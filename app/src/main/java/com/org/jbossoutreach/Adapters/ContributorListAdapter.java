package com.org.jbossoutreach.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.org.jbossoutreach.Models.ContributionModel;
import com.org.jbossoutreach.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class ContributorListAdapter extends RecyclerView.Adapter<ContributorListAdapter.ViewHolder> {
    Context context;
    ArrayList<ContributionModel> list;

    public ContributorListAdapter(ArrayList<ContributionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ContributorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contributorlistitem, parent, false);
        ContributorListAdapter.ViewHolder viewHolder = new ContributorListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorListAdapter.ViewHolder holder, int position) {

        holder.Name.setText(list.get(position).getName());
        holder.Handle.setText(("@" + list.get(position).getName()));
        Glide.with(context)
                .load(list.get(position).getAvatar())
                .into(holder.Userimage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView Name;
        public AppCompatTextView Handle;

        public AppCompatImageView Userimage;


        public ViewHolder(View itemView) {
            super(itemView);

            Name = (AppCompatTextView) itemView.findViewById(R.id.contributorname);
            Handle = (AppCompatTextView) itemView.findViewById(R.id.contributorhandle);
            Userimage = (AppCompatImageView) itemView.findViewById(R.id.contributorimage);


        }


    }
}
