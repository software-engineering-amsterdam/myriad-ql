package org.uva.hatt.taxform.ast.visitors;


import java.util.LinkedList;
import java.util.List;

public class ExceptionHandler {
    private List<Error> errors = new LinkedList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
