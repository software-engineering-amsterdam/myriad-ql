package com.matthewchapman.ql.app;

import com.matthewchapman.ql.gui.errors.ErrorDialogGenerator;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            Logger.getAnonymousLogger().log(Level.SEVERE, "Exception", e);
            new ErrorDialogGenerator().generateErrorListBox(e.getMessage(), "File Read Error", "There was an error reading the input file");
            return null;
        }
        return fileContents;
    }
}
