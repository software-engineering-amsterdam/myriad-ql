package ql.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 21-2-2017.
 */
public class ErrorHandler {
    private List<Error> errors = new ArrayList<>();
    private final boolean debugMode;

    public ErrorHandler() {
        this.debugMode = false;
    }

    public ErrorHandler(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public boolean foundErrors(){
        return !errors.isEmpty();
    }

    public void showErrors(){
        if (debugMode) {
            return;
        }

        for (Error error : errors) {
            System.err.println("Line " + error.getRow() + ": " + error.getMessage());
        }

        if(foundErrors()) {
            System.exit(1);
        }
    }
}
