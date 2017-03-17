package org.ql.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QLFile extends SourceFile {
    private final String SRC_FILE_EXT = "aql";

    public QLFile(String file) throws FileNotFoundException, IOException {
        super(file);
    }

    @Override
    public void throwFileNotFoundException() throws QLFileNotFoundException {
        throw new QLFileNotFoundException();
    }

    @Override
    public void throwIncorrectExtensionException() throws QLIncorrectExtensionException {
        throw new QLIncorrectExtensionException();
    }

    @Override
    public String getSourceExtension() {
        return SRC_FILE_EXT;
    }
}
