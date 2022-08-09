package com.example.chapter1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;

public class FileCopier{

    /**
     *
     */
    private static final String DATA_INBOX = "data/inbox";
    private static final String DATA_OUTBOX = "data/outbox";

    public static void main(String[] args) throws IOException {
        File inboxDirectory = new File(DATA_INBOX);
        File outboxDirectory = new File(DATA_OUTBOX);
        
        outboxDirectory.mkdir();
        
        File[] files = inboxDirectory.listFiles();
        for (File source : files) {
            if (source.isFile()) {
                File dest = new File(
                        outboxDirectory.getPath() 
                        + File.separator 
                        + source.getName()); 
                copyFile(source, dest);
            }
        }
           
    }

    private static void copyFile(File source, File dest) throws IOException {

        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[(int) source.length()];
        FileInputStream in = new FileInputStream(source);
        in.read(buffer);
        try {
            out.write(buffer);
        } finally {
            out.close();      
            in.close();
        }
    }

}