package ql.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 21-2-2017.
 */
public class ErrorHandler {
    private List<Error> errors = new ArrayList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    private boolean foundErrors(){
        return !errors.isEmpty();
    }

    public void showErrors(){
        for (Error error : errors) {
            System.err.println("Line " + error.getRow() + ": " + error.getMessage());
        }
        if(foundErrors()) {
            System.exit(1);
        }
    }
}
