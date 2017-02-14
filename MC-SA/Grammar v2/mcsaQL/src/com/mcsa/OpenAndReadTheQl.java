package com.mcsa;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sotos on 13/2/2017.
 */
class OpenAndReadTheQl {

     String QlRead () {
        String fileInString = "";

        //OpenAndReadTheQl.QlRead();

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileInString = fileInString.concat(line);
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

        return fileInString;
    }

}
