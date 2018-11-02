package com.org.jbossoutreach.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.org.jbossoutreach.Adapters.RepoListAdapter;
import com.org.jbossoutreach.ContributionActivity;
import com.org.jbossoutreach.Listeners.RecyclerTouchListener;
import com.org.jbossoutreach.Managers.RepositoryManager;
import com.org.jbossoutreach.Models.RepositoryModel;
import com.org.jbossoutreach.R;

import java.util.ArrayList;


public class RepositoryFragment extends Fragment {


    public RepositoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Repositories");
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        View rootView = inflater.inflate(R.layout.activity_repository, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        RecyclerView.Adapter mAdapter;
        ArrayList<RepositoryModel> list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());



        mAdapter = new RepoListAdapter(new RepositoryManager().repositorynames());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                Intent i = new Intent(getActivity(), ContributionActivity.class);
                i.putExtra("position", position);
                getActivity().startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

}
