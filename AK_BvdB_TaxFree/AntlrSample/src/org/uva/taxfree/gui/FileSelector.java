package org.uva.taxfree.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

// Constructs a file dialog an generated a new file based on this
public class FileSelector {
    public static File select() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File
                (".\\forms"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Tax forms (.txfrm)", "txfrm");
        chooser.setFileFilter(filter);
        String fileName = "";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getAbsolutePath();
        }
        return new File(fileName);
    }
}
