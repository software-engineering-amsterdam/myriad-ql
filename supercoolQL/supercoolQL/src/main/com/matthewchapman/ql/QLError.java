package com.matthewchapman.ql;

/**
 * Created by matt on 13/03/2017.
 */
public class QLError {

    private String message;

    public QLError(Integer stage, String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }

}
