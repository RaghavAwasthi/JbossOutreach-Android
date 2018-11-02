package com.org.jbossoutreach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.org.jbossoutreach.Adapters.RepoListAdapter;
import com.org.jbossoutreach.Listeners.RecyclerTouchListener;
import com.org.jbossoutreach.Managers.RepositoryManager;
import com.org.jbossoutreach.Models.RepositoryModel;

import java.util.ArrayList;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        RecyclerView recyclerView=findViewById(R.id.list);
        RecyclerView.Adapter mAdapter;
        ArrayList<RepositoryModel> list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);


        mAdapter=new RepoListAdapter(new RepositoryManager().repositorynames());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                Intent i= new Intent(getApplicationContext(),ContributionActivity.class);
                i.putExtra("position",position);
                getApplicationContext().startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
