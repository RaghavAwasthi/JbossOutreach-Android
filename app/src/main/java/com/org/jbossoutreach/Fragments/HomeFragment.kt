package com.org.jbossoutreach.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.org.jbossoutreach.Managers.ContributionManager
import com.org.jbossoutreach.Managers.RepositoryManager
import com.org.jbossoutreach.R

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        activity!!.title = "Home"
        val mb = RepositoryManager().gettopcontributor()
        val cm = ContributionManager().gettopcontributor(mb.contributionurl)
        (rootView.findViewById<View>(R.id.reponame) as AppCompatTextView).text = mb.name
        (rootView.findViewById<View>(R.id.repodescriptor) as AppCompatTextView).text = mb.description
        (rootView.findViewById<View>(R.id.contributorname) as AppCompatTextView).text = cm!![0].name
        (rootView.findViewById<View>(R.id.contributorhandle) as AppCompatTextView).text = "@" + cm[0].name
        Glide.with(context!!)
                .load(cm[0].avatar)
                .into(rootView.findViewById<View>(R.id.contributorimage) as ImageView)
        (rootView.findViewById<View>(R.id.repoimage) as AppCompatImageView).setImageResource(R.drawable.ic_repofavicon)
        if (cm.size > 1) {
            (rootView.findViewById<View>(R.id.contributorname2) as AppCompatTextView).text = cm[1].name
            (rootView.findViewById<View>(R.id.contributorhandle2) as AppCompatTextView).text = "@" + cm[1].name
            Glide.with(context!!)
                    .load(cm[1].avatar)
                    .into(rootView.findViewById<View>(R.id.contributorimage2) as ImageView)
        }
        if (cm.size > 2) {
            (rootView.findViewById<View>(R.id.contributorname3) as AppCompatTextView).text = cm[2].name
            (rootView.findViewById<View>(R.id.contributorhandle3) as AppCompatTextView).text = "@" + cm[2].name
            Glide.with(context!!)
                    .load(cm[2].avatar)
                    .into(rootView.findViewById<View>(R.id.contributorimage3) as ImageView)
        }
        return rootView
    }
}