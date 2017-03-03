package com.matthewchapman.ql.core;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sotos on 13/2/2017.
 * Refactored by matt on 24/2/2017
 *
 * Provides methods for reading a QL file into a String, and generating a filepicker
 */


class FileReader {

    private String resultString = "";

    String QlRead() throws InvocationTargetException, InterruptedException {
        //Put the UI rendering on a separate thread for OSX
        //TODO extract method & remove lambda to allow resultString removal
        EventQueue.invokeAndWait(() -> {

            String fileInString = "";
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select ql Input File");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                fileInString = readFile(selectedFile);
            }
            resultString = fileInString;
        });

        return resultString;
    }

    String readFile(File selectedFile) {
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
