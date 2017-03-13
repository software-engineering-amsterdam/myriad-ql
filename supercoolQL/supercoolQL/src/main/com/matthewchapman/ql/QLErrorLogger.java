package com.matthewchapman.ql;

import java.util.List;

/**
 * Created by matt on 13/03/2017.
 */
public class QLErrorLogger {

    private List<QLError> errors;

    public void printErrors() {
        for(QLError error : errors) {
            System.out.println(error);
        }
    }

}
