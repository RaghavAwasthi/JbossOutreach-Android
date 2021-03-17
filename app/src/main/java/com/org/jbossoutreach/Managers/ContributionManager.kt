package com.org.jbossoutreach.Managers

import android.os.AsyncTask
import com.org.jbossoutreach.Models.ContributionModel
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class ContributionManager : AsyncTask<String?, Void?, String>() {
    protected override fun doInBackground(vararg urlString: String): String {
        return ContributionManager().readUrl(urlString[0])
    }

    fun contributornames(url: String?): ArrayList<ContributionModel> {
        val contibutorslist = ArrayList<ContributionModel>()
        try {
            val data = RepositoryManager().execute(url).get()
            val jsonArr = JSONArray(data)
            for (i in 0 until jsonArr.length()) {
                val jsonObj = jsonArr.getJSONObject(i)
                val rm = ContributionModel()
                rm.name = jsonObj["login"].toString()
                rm.avatar = jsonObj["avatar_url"].toString()
                rm.contributions = jsonObj["contributions"].toString().toInt()
                contibutorslist.add(rm)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return contibutorslist
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

    fun gettopcontributor(url: String?): ArrayList<ContributionModel> {
        val list = contributornames(url)
        val n = list.size
        for (i in 0 until n - 1) for (j in 0 until n - i - 1) if (list[j].contributions > list[j + 1].contributions) {
            var tmp: ContributionModel
            // swap tmp
            tmp = list[j]
            list[j] = list[j + 1]
            list[j + 1] = tmp
        }
        return list
    }
}