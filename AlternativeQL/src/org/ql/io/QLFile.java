package org.ql.io;

import org.io.SourceFile;

import java.io.IOException;

public class QLFile extends SourceFile {

    public QLFile(String file) throws IOException {
        super(file);
    }

    @Override
    protected String extension() {
        return "aql";
    }
}
