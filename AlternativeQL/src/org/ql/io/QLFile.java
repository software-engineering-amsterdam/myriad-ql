package org.ql.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QLFile {
    private static final String SRC_FILE_EXT = "aql";
    private final String file;

    public QLFile(String file) throws QLFileNotFoundException, QLIncorrectExtensionException {
        if (fileExists(file)) {
            throw new QLFileNotFoundException();
        }

        if (!isExtensionCorrect(file)) {
            throw new QLIncorrectExtensionException();
        }

        this.file = file;
    }

    public InputStream openStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    private boolean isExtensionCorrect(String file) {
        return file.matches("^.+?\\." + SRC_FILE_EXT + "$");
    }

    private boolean fileExists(String file) {
        return !Files.exists(Paths.get(file));
    }
}
