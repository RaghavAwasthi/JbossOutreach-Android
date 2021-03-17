package com.org.jbossoutreach.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.org.jbossoutreach.Adapters.RepoListAdapter
import com.org.jbossoutreach.ContributionActivity
import com.org.jbossoutreach.Listeners.RecyclerTouchListener
import com.org.jbossoutreach.Listeners.RecyclerTouchListener.ClickListener
import com.org.jbossoutreach.Managers.RepositoryManager
import com.org.jbossoutreach.Models.RepositoryModel
import com.org.jbossoutreach.R
import java.util.*

class RepositoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity!!.title = "Repositories"
        val textView = TextView(activity)
        textView.setText(R.string.hello_blank_fragment)
        val rootView = inflater.inflate(R.layout.activity_repository, container, false)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.list)
        val mAdapter: RecyclerView.Adapter<*>
        val list = ArrayList<RepositoryModel>()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        mAdapter = RepoListAdapter(RepositoryManager().repositorynames())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        recyclerView.addOnItemTouchListener(RecyclerTouchListener(activity, recyclerView, object : ClickListener {
            override fun onClick(view: View?, position: Int) {
                val i = Intent(activity, ContributionActivity::class.java)
                i.putExtra("position", position)
                activity!!.startActivity(i)
            }

            override fun onLongClick(view: View?, position: Int) {}
        }))
        return rootView
    }
}