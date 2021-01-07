package com.appmerc.shmup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
        appendToFileUtf8("scores.txt", "Name: " + name + ", Score: " + score);
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
}
