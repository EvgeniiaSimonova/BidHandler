package com.company.tools;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private File file;

    public Reader(File file) {
        this.file = file;
    }

    public List<String> getStringList() {
        List<String> strings = new LinkedList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                strings.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
