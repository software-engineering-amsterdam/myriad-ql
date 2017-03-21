package org.uva.taxfree.ql.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utilities {
    static boolean RemoveFile(String relativePath) {
        Path p = FileSystems.getDefault().getPath(relativePath);
        try {
            Files.deleteIfExists(p);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        File f = new File("log.err");
        return (false == f.isFile());
    }
}
