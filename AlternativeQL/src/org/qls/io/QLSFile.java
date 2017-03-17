package org.qls.io;

import org.ql.io.QLFileNotFoundException;
import org.ql.io.QLIncorrectExtensionException;
import org.ql.io.SourceFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QLSFile extends SourceFile {
    private final String SRC_FILE_EXT = "aqls";

    public QLSFile(String file) throws FileNotFoundException, IOException {
        super(file);
    }

    @Override
    public void throwFileNotFoundException() throws QLSFileNotFoundException {
        throw new QLSFileNotFoundException();
    }

    @Override
    public void throwIncorrectExtensionException() throws QLIncorrectExtensionException {

    }

    @Override
    public String getSourceExtension() {
        return SRC_FILE_EXT;
    }
}
