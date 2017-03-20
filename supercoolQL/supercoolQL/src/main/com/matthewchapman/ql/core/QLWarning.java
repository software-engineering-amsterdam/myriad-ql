package com.matthewchapman.ql.core;

/**
 * Created by matt on 19/03/2017.
 */
public class QLWarning extends QLError {
    public QLWarning(int line, int column, String id, String message) {
        super(line, column, id, message);
    }

    @Override
    public String toString() {
        return "Warning: " + this.getLine() + ":" + this.getColumn() + " - " + this.getId() + " : " + this.getMessage();
    }
}
