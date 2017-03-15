package com.matthewchapman.ql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 13/03/2017.
 */
class QLErrorLogger {

    private List<QLError> errors;

    public QLErrorLogger() {
        errors = new ArrayList<>();
    }

    public void printErrors() {
        for (QLError error : errors) {
            System.out.println(error);
        }
    }

}
