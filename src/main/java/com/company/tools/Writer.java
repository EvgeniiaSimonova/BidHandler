package com.company.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    private File file;

    public Writer(File file) {
        this.file = file;
    }

    public void write(List<String> fileStrings) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String s: fileStrings) {
                fileWriter.write(s);
                fileWriter.write(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
