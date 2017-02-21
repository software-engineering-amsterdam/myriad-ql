package com.mcsa.QL;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sotos on 13/2/2017.
 */


public class OpenAndReadTheQl {

    private String resultString = "";

    public String QlRead() throws InvocationTargetException, InterruptedException {

        EventQueue.invokeAndWait(() -> {

            String fileInString = "";

            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Select QL Input File");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        fileInString = fileInString.concat(line+'\n');
                        // process the line.
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No Selection ");
            }

            resultString = fileInString;
        });

        return resultString;
    }
}
