package com.org.jbossoutreach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.org.jbossoutreach.Adapters.ContributorListAdapter
import com.org.jbossoutreach.Managers.ContributionManager
import com.org.jbossoutreach.Managers.RepositoryManager
import com.org.jbossoutreach.Models.RepositoryModel
import java.util.*

class ContributionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contribution)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        val mAdapter: RecyclerView.Adapter<*>
        val list = ArrayList<RepositoryModel>()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val pos = intent.extras["position"] as Int
        mAdapter = ContributorListAdapter(ContributionManager().contributornames(RepositoryManager().repositorynames()[pos].contributionurl), this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
    }
}