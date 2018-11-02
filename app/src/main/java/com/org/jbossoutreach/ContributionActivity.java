package com.org.jbossoutreach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.org.jbossoutreach.Adapters.ContributorListAdapter;
import com.org.jbossoutreach.Managers.ContributionManager;
import com.org.jbossoutreach.Managers.RepositoryManager;
import com.org.jbossoutreach.Models.RepositoryModel;

import java.util.ArrayList;

public class ContributionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.Adapter mAdapter;
        ArrayList<RepositoryModel> list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        int pos = (int) getIntent().getExtras().get("position");

        mAdapter = new ContributorListAdapter(new ContributionManager().contributornames(new RepositoryManager().repositorynames().get(pos).getContributionurl()), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    }
}