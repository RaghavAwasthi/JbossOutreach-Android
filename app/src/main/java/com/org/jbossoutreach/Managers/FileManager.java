package com.org.jbossoutreach.Managers;


import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {
    String path;

    public void setPath(String path) {
        this.path = path;
    }


    public Boolean fileexists() {
        File f = new File(path);
        return f.exists();
    }



    public Boolean writetofile(String contents) {
        Boolean status = false;
        try (FileWriter fw = new FileWriter(path);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(contents);
            out.flush();
            out.close();
            bw.close();

        } catch (IOException e) {


        }
        return status;
    }

    public String readfile() {
        String jsonstring = null;
        try {
            BufferedReader br = null;

            try {
                br = new BufferedReader(
                        new FileReader(path));
                jsonstring = br.readLine();
            } catch (FileNotFoundException ae) {
                ae.printStackTrace();

            } finally {
                if (!(br == null))
                    br.close();
            }
        } catch (IOException ae) {
            ae.printStackTrace();
        }
        return jsonstring;
    }


}

