package org.ql.ast;

public class SourceLocation {
    private int line;
    private int column;

    public SourceLocation(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "At line: " + this.line +
                ", column " + this.column;
    }
}
