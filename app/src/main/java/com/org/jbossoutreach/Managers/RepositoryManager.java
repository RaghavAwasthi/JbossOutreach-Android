package com.org.jbossoutreach.Managers;

import android.os.AsyncTask;
import android.os.Environment;

import com.org.jbossoutreach.Models.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RepositoryManager extends AsyncTask<String, Void, String> {

    String rootpath = Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected String doInBackground(String... urlString) {

        String data = new RepositoryManager().readUrl(urlString[0]);
        return data;
    }

    public ArrayList<RepositoryModel> repositorynames() {
        ArrayList<RepositoryModel> repolist = new ArrayList<>();
        FileManager fm = new FileManager();
        fm.setPath(rootpath + "/repo.json");
        //if (fm.fileexists()) {
        //  String data = fm.readfile();
        //TypeToken<ArrayList<RepositoryModel>> token = new TypeToken<ArrayList<RepositoryModel>>() {
        //};
        //ArrayList<RepositoryModel> tmp = new Gson().fromJson(data, token.getType());
        //return tmp;
        //} else {


        String url = "https://api.github.com/orgs/JBossOutreach/repos";
        try {
            String data = new RepositoryManager().execute(url).get();
            JSONArray jsonArr = new JSONArray(data);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                RepositoryModel rm = new RepositoryModel();
                rm.setName(jsonObj.get("name").toString());
                rm.setDescription(jsonObj.get("description").toString());
                rm.setContributionurl(jsonObj.get("contributors_url").toString());
                rm.setCommitcount(Integer.parseInt(jsonObj.get("forks_count").toString()));
                if (rm.getDescription().equals("null"))
                    rm.setDescription("Amazing project from JBoss in its early stage");

                repolist.add(rm);
            }


        } catch (Exception e) {
            e.printStackTrace();
            //  }
        }
        return repolist;
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

    public RepositoryModel gettopcontributor() {
        int count = 0;
        generatecontributorscache();
        ArrayList<RepositoryModel> list = repositorynames();
        RepositoryModel tmp = new RepositoryModel();
        if (list != null) {
            for (RepositoryModel data : list) {
                if (count < data.getCommitcount()) {
                    tmp.setName(data.getName());
                    tmp.setCommitcount(data.getCommitcount());
                    tmp.setDescription(data.getDescription());
                    tmp.setContributionurl(data.getContributionurl());
                    tmp.setForkcunt(data.getForkcunt());
                }


            }
        }


        return tmp;
    }

    public void generatecontributorscache() {
        ArrayList<RepositoryModel> repolist = new ArrayList<>();
        String url = "https://api.github.com/orgs/JBossOutreach/repos";
        try {
            String data = new RepositoryManager().execute(url).get();
            JSONArray jsonArr = new JSONArray(data);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                RepositoryModel rm = new RepositoryModel();
                rm.setName(jsonObj.get("name").toString());
                rm.setDescription(jsonObj.get("description").toString());
                rm.setContributionurl(jsonObj.get("contributors_url").toString());
                rm.setCommitcount(Integer.parseInt(jsonObj.get("forks_count").toString()));
                if (rm.getDescription().equals("null"))
                    rm.setDescription("Amazing project from JBoss in its early stage");

                repolist.add(rm);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        FileManager fm = new FileManager();
        fm.setPath(rootpath + "/repo.json");

        Gson gs = new Gson();
        TypeToken<ArrayList<RepositoryModel>> token = new TypeToken<ArrayList<RepositoryModel>>() {
        };
        String ar = gs.toJson(repolist, token.getType());
        fm.writetofile(ar);

    }


}