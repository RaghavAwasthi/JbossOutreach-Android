package com.org.jbossoutreach.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.org.jbossoutreach.Models.RepositoryModel
import com.org.jbossoutreach.R
import java.util.*

class RepoListAdapter(var list: ArrayList<RepositoryModel?>?) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repolistitem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.RepoName.text = list!![position].getName()
        holder.RepoDesc.text = list!![position].getDescription()
        holder.Repoimage.setImageResource(R.drawable.ic_repofavicon)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var RepoName: AppCompatTextView
        var RepoDesc: AppCompatTextView
        var Repoimage: AppCompatImageView

        init {
            RepoName = itemView.findViewById<View>(R.id.reponame) as AppCompatTextView
            RepoDesc = itemView.findViewById<View>(R.id.repodescriptor) as AppCompatTextView
            Repoimage = itemView.findViewById<View>(R.id.repoimage) as AppCompatImageView
        }
    }
}