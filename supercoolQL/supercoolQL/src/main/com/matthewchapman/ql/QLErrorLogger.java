package com.matthewchapman.ql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 13/03/2017.
 */
public class QLErrorLogger {

    private List<QLError> errors;

    public QLErrorLogger() {
        errors = new ArrayList<>();
    }

    public void addError(int line, int column, String id, String message) {
        errors.add(new QLError(line, column, id, message));
    }

    public int getErrorNumber() { return this.errors.size(); }

    public void printErrors() {
        for (QLError error : errors) {
            System.err.println(error);
        }
    }

}
