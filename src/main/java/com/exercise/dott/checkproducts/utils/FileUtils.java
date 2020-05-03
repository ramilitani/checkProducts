package com.exercise.dott.checkproducts.utils;

import java.io.*;

public final class FileUtils {

    public static void write(File file, String text)
    {
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(text);
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing in the log file");
        }
    }

    public static void cleanLogFile(String file)
    {
        File logFile = new File(file);
        if (logFile.exists()) {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(logFile, "rw");
                raf.setLength(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
