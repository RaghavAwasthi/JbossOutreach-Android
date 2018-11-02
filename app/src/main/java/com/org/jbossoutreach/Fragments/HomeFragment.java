package com.org.jbossoutreach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.org.jbossoutreach.Managers.ContributionManager;
import com.org.jbossoutreach.Managers.RepositoryManager;
import com.org.jbossoutreach.Models.ContributionModel;
import com.org.jbossoutreach.Models.RepositoryModel;
import com.org.jbossoutreach.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_home, container, false);
getActivity().setTitle("Home");

        RepositoryModel mb=new RepositoryManager().gettopcontributor();
        ContributionModel cm=new ContributionManager().gettopcontributor(mb.getContributionurl());
        ((AppCompatTextView)rootView.findViewById(R.id.reponame)).setText(mb.getName());
        ((AppCompatTextView)rootView.findViewById(R.id.repodescriptor)).setText(mb.getDescription());
        ((AppCompatTextView)rootView.findViewById(R.id.contributorname)).setText(cm.getName());
        ((AppCompatTextView)rootView.findViewById(R.id.contributorhandle)).setText("@"+cm.getName());
        Glide.with(getContext())
                .load(cm.getAvatar())
                .into((ImageView) rootView.findViewById(R.id.contributorimage));
        ((AppCompatImageView)rootView.findViewById(R.id.repoimage)).setImageResource(R.drawable.ic_repofavicon);


        return rootView;
    }
}
