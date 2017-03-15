package com.matthewchapman.ql.core;

/**
 * Created by matt on 13/03/2017.
 */
public class QLError {

    private final String message;
    private final int line;
    private final int column;
    private final String id;

    public QLError(int line, int column, String id, String message) {
        this.message = message;
        this.line = line;
        this.column = column;
        this.id = id;
    }

    public String toString() {
        return "Error: " + this.line + ":" + this.column + " - " + this.id + " : " + this.message;
    }

}
