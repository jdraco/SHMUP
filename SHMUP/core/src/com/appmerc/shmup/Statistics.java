package com.appmerc.shmup;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Statistics {
    public static int score;
    public static String name;

    public void init()
    {
        score = 0;
        name = new String("Player");
    }

    public void publishResult()
    {
        //"ftp://levram:marvel@118.189.212.47/scores.txt;type=i"
        appendToFileOnlne("ftp://levram:marvel@118.189.212.47/scores.txt", "Name: " + name + ", Score: " + score);
        //appendToFileUtf8("D:\\Work\\GitHub\\SHMUP\\SHMUP\\android\\assets\\scores.txt", "Name: " + name + ", Score: " + score);
    }

    public static void appendToFileUtf8(String file, String data) {
        try {
            FileOutputStream fout = new FileOutputStream(file, true);
            OutputStreamWriter outwrite = new OutputStreamWriter(fout, "UTF-8");
            outwrite.write(data);
            outwrite.close();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: "+file+" "+e.getMessage());
        }
    }

    public static void appendToFileOnlne(String file, String data) {
        try {
            URL  url = new URL(file);
            URLConnection urlc = url.openConnection();
            OutputStream os = urlc.getOutputStream(); // To upload
            OutputStream buffer = new BufferedOutputStream(os);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(data);
            output.flush();
            /*OutputStreamWriter output = new OutputStreamWriter(buffer);
            output.write(data);*/
            buffer.close();
            os.close();
            output.close();

        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: "+file+" "+e.getMessage());
        }
    }
}
