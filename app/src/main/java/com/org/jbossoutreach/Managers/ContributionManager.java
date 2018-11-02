package com.org.jbossoutreach.Managers;

import android.os.AsyncTask;

import com.org.jbossoutreach.Models.ContributionModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ContributionManager  extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urlString) {

        String data = new ContributionManager().readUrl(urlString[0]);
        return data;
    }

    public ArrayList<ContributionModel> contributornames(String url) {
        ArrayList<ContributionModel> contibutorslist = new ArrayList<>();

        try {
            String data = new RepositoryManager().execute(url).get();
            JSONArray jsonArr = new JSONArray(data);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                ContributionModel rm = new ContributionModel();
                rm.setName(jsonObj.get("login").toString());
                rm.setAvatar(jsonObj.get("avatar_url").toString());
                rm.setContributions(Integer.parseInt(jsonObj.get("contributions").toString()));

                contibutorslist.add(rm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contibutorslist;
    }


    private String readUrl(String urlString) {

        String tmp = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                }
        }
        return tmp;
    }

    public ContributionModel gettopcontributor(String url) {
        int count = 0;
        ArrayList<ContributionModel> list = contributornames(url);
        ContributionModel tmp = new ContributionModel();
        for (ContributionModel data : list) {
            if (count < data.getContributions()) {
                tmp.setName(data.getName());
                tmp.setAvatar(data.getAvatar());
                tmp.setContributions(data.getContributions());
            }


        }


        return tmp;
    }




}