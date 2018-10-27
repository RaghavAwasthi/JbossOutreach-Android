package com.org.jbossoutreach.Managers;

import android.os.AsyncTask;

import com.org.jbossoutreach.Models.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RepositoryManager extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urlString) {

        String data = new RepositoryManager().readUrl(urlString[0]);
        return data;
    }

    public ArrayList<RepositoryModel> repositorynames() {
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
                System.out.println(jsonObj.get("name"));
                repolist.add(rm);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void getImageURL() {

    }


}