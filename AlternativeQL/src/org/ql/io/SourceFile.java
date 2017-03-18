package org.ql.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class SourceFile {
    protected final String file;

    public SourceFile(String file) throws FileNotFoundException, IOException {
        if (fileExists(file)) {
            throwFileNotFoundException();
        }

        if (!isExtensionCorrect(file)) {
            throwIncorrectExtensionException();
        }

        this.file = file;
    }

    public InputStream openStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    protected boolean isExtensionCorrect(String file) {
        return file.matches("^.+?\\." + getSourceExtension() + "$");
    }

    protected boolean fileExists(String file) {
        return !Files.exists(Paths.get(file));
    }

    public abstract void throwFileNotFoundException() throws IOException;

    public abstract void throwIncorrectExtensionException() throws IOException;

    public abstract String getSourceExtension();
}
