package com.org.jbossoutreach.chat

import com.org.jbossoutreach.chat.MessageAdapter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ChildEventListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.org.jbossoutreach.R
import com.org.jbossoutreach.chat.ChatFragment
import com.org.jbossoutreach.chat.ChatMessage
import android.content.Intent
import android.text.TextWatcher
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import com.google.firebase.auth.FirebaseUser
import android.app.Activity
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.util.ArrayList

class ChatFragment : Fragment() {
    private var mMessageListView: ListView? = null
    private var mMessageAdapter: MessageAdapter? = null
    private var mProgressBar: ProgressBar? = null
    private var mPhotoPickerButton: ImageButton? = null
    private var mMessageEditText: EditText? = null
    private var mSendButton: Button? = null
    private var mUsername: String? = null

    // Firebase instance variables
    private var mFirebaseDatabase: FirebaseDatabase? = null
    private var mMessagesDatabaseReference: DatabaseReference? = null
    private var mChildEventListener: ChildEventListener? = null
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mAuthStateListener: AuthStateListener? = null
    private var mFirebaseStorage: FirebaseStorage? = null
    private var mChatPhotosStorageReference: StorageReference? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val tmp = inflater.inflate(R.layout.fragment_chat, container, false)
        mUsername = ANONYMOUS
        activity!!.title = "Jboss-Chat"

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mFirebaseDatabase!!.setPersistenceEnabled(true)
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseStorage = FirebaseStorage.getInstance()
        mMessagesDatabaseReference = mFirebaseDatabase!!.reference.child("messages")
        mChatPhotosStorageReference = mFirebaseStorage!!.reference.child("chat_photos")

        // Initialize references to views
        mProgressBar = tmp.findViewById<View>(R.id.progressBar) as ProgressBar
        mMessageListView = tmp.findViewById<View>(R.id.messageListView) as ListView
        mPhotoPickerButton = tmp.findViewById<View>(R.id.photoPickerButton) as ImageButton
        mMessageEditText = tmp.findViewById<View>(R.id.messageEditText) as EditText
        mSendButton = tmp.findViewById<View>(R.id.sendButton) as Button

        // Initialize message ListView and its adapter
        val friendlyMessages: List<ChatMessage> = ArrayList()
        mMessageAdapter = MessageAdapter(context, R.layout.item_message, friendlyMessages)
        mMessageListView!!.adapter = mMessageAdapter

        // Initialize progress bar
        mProgressBar!!.visibility = ProgressBar.INVISIBLE

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER)
        }

        // Enable Send button when there's text to send
        mMessageEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.toString().trim { it <= ' ' }.length > 0) {
                    mSendButton!!.isEnabled = true
                } else {
                    mSendButton!!.isEnabled = false
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        mMessageEditText!!.filters = arrayOf<InputFilter>(LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        // Send button sends a message and clears the EditText
        mSendButton!!.setOnClickListener {
            val friendlyMessage = ChatMessage(mMessageEditText!!.text.toString(), mUsername, null)
            mMessagesDatabaseReference!!.push().setValue(friendlyMessage)

            // Clear input box
            mMessageEditText!!.setText("")
        }
        mAuthStateListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                onSignedInInitialize(user.displayName)
            }
        }
        return tmp
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_PHOTO_PICKER && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data!!.data

            // Get a reference to store file at chat_photos/<FILENAME>
            val photoRef = mChatPhotosStorageReference!!.child(selectedImageUri.lastPathSegment)

            // Upload file to Firebase Storage
            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(activity!!) { taskSnapshot ->
                        // When the image has successfully uploaded, we get its download URL
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                            val friendlyMessage = ChatMessage(null, mUsername, uri.toString())
                            mMessagesDatabaseReference!!.push().setValue(friendlyMessage)
                        }

                        // Set the download URL to the message box, so that the user can send it to the database
                    }
        }
    }

    private fun onSignedInInitialize(username: String?) {
        mUsername = username
        attachDatabaseReadListener()
    }

    private fun attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    val friendlyMessage = dataSnapshot.getValue(ChatMessage::class.java)
                    mMessageAdapter!!.add(friendlyMessage)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
                override fun onCancelled(databaseError: DatabaseError) {}
            }
            mMessagesDatabaseReference!!.addChildEventListener(mChildEventListener as ChildEventListener)
        }
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth!!.addAuthStateListener(mAuthStateListener!!)
    }

    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null) {
            mFirebaseAuth!!.removeAuthStateListener(mAuthStateListener!!)
        }
        mMessageAdapter!!.clear()
        detachDatabaseReadListener()
    }

    private fun detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mMessagesDatabaseReference!!.removeEventListener(mChildEventListener!!)
            mChildEventListener = null
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        const val ANONYMOUS = "anonymous"
        const val DEFAULT_MSG_LENGTH_LIMIT = 1000
        const val RC_SIGN_IN = 1
        private const val RC_PHOTO_PICKER = 2
    }
}