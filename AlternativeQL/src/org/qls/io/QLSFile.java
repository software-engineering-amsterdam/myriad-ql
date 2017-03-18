package org.qls.io;

import org.io.SourceFile;

import java.io.IOException;

public class QLSFile extends SourceFile {

    public QLSFile(String file) throws IOException {
        super(file);
    }

    @Override
    public String extension() {
        return "aqls";
    }
}
