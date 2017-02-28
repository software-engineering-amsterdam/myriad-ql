package org.ql.ast;

// TODO better name (like src location?? )
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

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "At line: " + this.line +
                ", column " + this.column;
    }
}
