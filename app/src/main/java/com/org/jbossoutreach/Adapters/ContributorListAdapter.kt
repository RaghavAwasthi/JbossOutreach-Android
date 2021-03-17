package com.org.jbossoutreach.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.org.jbossoutreach.Models.ContributionModel
import com.org.jbossoutreach.R
import java.util.*

class ContributorListAdapter(var list: ArrayList<ContributionModel?>?, var context: Context) : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contributorlistitem, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Name.text = list!![position].getName()
        holder.Handle.text = "@" + list!![position].getName()
        Glide.with(context)
                .load(list!![position].getAvatar())
                .into(holder.Userimage)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: AppCompatTextView
        var Handle: AppCompatTextView
        var Userimage: AppCompatImageView

        init {
            Name = itemView.findViewById<View>(R.id.contributorname) as AppCompatTextView
            Handle = itemView.findViewById<View>(R.id.contributorhandle) as AppCompatTextView
            Userimage = itemView.findViewById<View>(R.id.contributorimage) as AppCompatImageView
        }
    }
}