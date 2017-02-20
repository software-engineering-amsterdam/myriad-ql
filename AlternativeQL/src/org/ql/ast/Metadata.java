package org.ql.ast;

public class Metadata {
    private int line;
    private int column;
    private String filename;

    public Metadata(int line, int column, String filename) {
        this.line = line;
        this.column = column;
        this.filename = filename;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "At line: " + this.line +
                ", column " + this.column +
                ", filename" + this.filename;
    }
}
