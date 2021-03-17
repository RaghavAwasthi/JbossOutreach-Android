package com.org.jbossoutreach.Managers

import java.io.*

class FileManager {
    var path: String? = null
    fun setPath(path: String?) {
        this.path = path
    }

    fun fileexists(): Boolean {
        val f = File(path)
        return f.exists()
    }

    fun writetofile(contents: String?): Boolean {
        val status = false
        try {
            FileWriter(path).use { fw ->
                BufferedWriter(fw).use { bw ->
                    PrintWriter(bw).use { out ->
                        out.println(contents)
                        out.flush()
                        out.close()
                        bw.close()
                    }
                }
            }
        } catch (e: IOException) {
        }
        return status
    }

    fun readfile(): String? {
        var jsonstring: String? = null
        try {
            var br: BufferedReader? = null
            try {
                br = BufferedReader(
                        FileReader(path))
                jsonstring = br.readLine()
            } catch (ae: FileNotFoundException) {
                ae.printStackTrace()
            } finally {
                br?.close()
            }
        } catch (ae: IOException) {
            ae.printStackTrace()
        }
        return jsonstring
    }
}