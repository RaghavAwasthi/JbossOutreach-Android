package com.org.jbossoutreach.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.jbossoutreach.Models.RepositoryModel;
import com.org.jbossoutreach.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    ArrayList<RepositoryModel> list;


    public RepoListAdapter(ArrayList<RepositoryModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repolistitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.RepoName.setText(list.get(position).getName());
        holder.RepoDesc.setText(list.get(position).getDescription());
        holder.Repoimage.setImageResource(R.drawable.ic_repofavicon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView RepoName;
        public AppCompatTextView RepoDesc;
        public AppCompatImageView Repoimage;


        public ViewHolder(View itemView) {
            super(itemView);

            RepoName = (AppCompatTextView) itemView.findViewById(R.id.reponame);
            RepoDesc = (AppCompatTextView) itemView.findViewById(R.id.repodescriptor);
            Repoimage = (AppCompatImageView) itemView.findViewById(R.id.repoimage);


        }


    }
}
