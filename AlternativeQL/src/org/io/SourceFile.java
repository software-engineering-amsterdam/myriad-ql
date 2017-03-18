package org.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class SourceFile {
    private final String file;

    public SourceFile(String file) throws IOException {
        if (fileExists(file)) {
            throw new FileNotFoundException();
        }

        if (!isExtensionCorrect(file)) {
            throw new IncorrectExtensionException();
        }

        this.file = file;
    }

    public InputStream openStream() throws IOException {
        return new FileInputStream(file);
    }

    private boolean isExtensionCorrect(String file) {
        return file.matches("^.+?\\." + extension() + "$");
    }

    private boolean fileExists(String file) {
        return !Files.exists(Paths.get(file));
    }

    protected abstract String extension();
}
