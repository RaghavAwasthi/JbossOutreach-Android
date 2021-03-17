package com.org.jbossoutreach.Managers

import android.os.AsyncTask
import android.os.Environment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.org.jbossoutreach.Models.RepositoryModel
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class RepositoryManager : AsyncTask<String?, Void?, String>() {
    var rootpath = Environment.getExternalStorageDirectory().absolutePath
    protected override fun doInBackground(vararg urlString: String): String {
        return RepositoryManager().readUrl(urlString[0])
    }

    fun repositorynames(): ArrayList<RepositoryModel?> {
        val repolist = ArrayList<RepositoryModel?>()
        val fm = FileManager()
        fm.setPath("$rootpath/repo.json")
        //if (fm.fileexists()) {
        //  String data = fm.readfile();
        //TypeToken<ArrayList<RepositoryModel>> token = new TypeToken<ArrayList<RepositoryModel>>() {
        //};
        //ArrayList<RepositoryModel> tmp = new Gson().fromJson(data, token.getType());
        //return tmp;
        //} else {
        val url = "https://api.github.com/orgs/JBossOutreach/repos"
        try {
            val data = RepositoryManager().execute(url).get()
            val jsonArr = JSONArray(data)
            for (i in 0 until jsonArr.length()) {
                val jsonObj = jsonArr.getJSONObject(i)
                val rm = RepositoryModel()
                rm.name = jsonObj["name"].toString()
                rm.description = jsonObj["description"].toString()
                rm.contributionurl = jsonObj["contributors_url"].toString()
                rm.commitcount = jsonObj["forks_count"].toString().toInt()
                if (rm.description == "null") rm.description = "Amazing project from JBoss in its early stage"
                repolist.add(rm)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            //  }
        }
        return repolist
    }

    private fun readUrl(urlString: String): String {
        val tmp = ""
        var reader: BufferedReader? = null
        try {
            val url = URL(urlString)
            reader = BufferedReader(InputStreamReader(url.openStream()))
            val buffer = StringBuffer()
            var read: Int
            val chars = CharArray(1024)
            while (reader.read(chars).also { read = it } != -1) buffer.append(chars, 0, read)
            return buffer.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (reader != null) try {
                reader.close()
            } catch (e: IOException) {
            }
        }
        return tmp
    }

    fun gettopcontributor(): RepositoryModel {
        val count = 0
        generatecontributorscache()
        val list = repositorynames()
        val tmp = RepositoryModel()
        if (list != null) {
            for (data in list) {
                if (count < data.getCommitcount()) {
                    tmp.name = data.getName()
                    tmp.commitcount = data.getCommitcount()
                    tmp.description = data.getDescription()
                    tmp.contributionurl = data.getContributionurl()
                    tmp.forkcunt = data.getForkcunt()
                }
            }
        }
        return tmp
    }

    fun generatecontributorscache() {
        val repolist = ArrayList<RepositoryModel>()
        val url = "https://api.github.com/orgs/JBossOutreach/repos"
        try {
            val data = RepositoryManager().execute(url).get()
            val jsonArr = JSONArray(data)
            for (i in 0 until jsonArr.length()) {
                val jsonObj = jsonArr.getJSONObject(i)
                val rm = RepositoryModel()
                rm.name = jsonObj["name"].toString()
                rm.description = jsonObj["description"].toString()
                rm.contributionurl = jsonObj["contributors_url"].toString()
                rm.commitcount = jsonObj["forks_count"].toString().toInt()
                if (rm.description == "null") rm.description = "Amazing project from JBoss in its early stage"
                repolist.add(rm)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fm = FileManager()
        fm.setPath("$rootpath/repo.json")
        val gs = Gson()
        val token: TypeToken<ArrayList<RepositoryModel>> = object : TypeToken<ArrayList<RepositoryModel?>?>() {}
        val ar = gs.toJson(repolist, token.type)
        fm.writetofile(ar)
    }
}