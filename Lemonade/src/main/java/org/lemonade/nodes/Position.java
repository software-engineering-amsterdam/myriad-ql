package org.lemonade.nodes;

public class Position {
    private int line;
    private int column;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public String toString() {
        return Integer.toString(line) + ":" + Integer.toString(column);
    }
}
