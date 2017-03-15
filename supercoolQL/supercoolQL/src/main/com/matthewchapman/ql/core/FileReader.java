package com.matthewchapman.ql.core;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by matt on 15/03/2017
 */


public class FileReader {

    public String readFile(File selectedFile) {
        String fileContents = "";
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(selectedFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                fileContents = fileContents.concat(line + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }
}
