package com.org.jbossoutreach

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.org.jbossoutreach.Adapters.RepoListAdapter
import com.org.jbossoutreach.Listeners.RecyclerTouchListener
import com.org.jbossoutreach.Listeners.RecyclerTouchListener.ClickListener
import com.org.jbossoutreach.Managers.RepositoryManager
import com.org.jbossoutreach.Models.RepositoryModel
import java.util.*

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        val mAdapter: RecyclerView.Adapter<*>
        val list = ArrayList<RepositoryModel>()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mAdapter = RepoListAdapter(RepositoryManager().repositorynames())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView, object : ClickListener {
            override fun onClick(view: View?, position: Int) {
                val i = Intent(applicationContext, ContributionActivity::class.java)
                i.putExtra("position", position)
                applicationContext.startActivity(i)
            }

            override fun onLongClick(view: View?, position: Int) {}
        }))
    }
}