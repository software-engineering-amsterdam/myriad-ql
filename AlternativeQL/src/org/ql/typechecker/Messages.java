package org.ql.typechecker;

import org.ql.ast.Node;
import org.ql.ast.SourceLocation;
import org.ql.typechecker.expression.TypeError;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private List<String> errors = new ArrayList<>();

    public void addError(TypeError error) {
        addError(error.getMessage(), error.getNode());
    }

    public void addError(String message, Node node) {
        SourceLocation sourceLocation = node.getSourceLocation();

        if (sourceLocation != null) {
            addError(message + " on line " + sourceLocation.getLine() + ":" + sourceLocation.getColumn());
        } else {
            addError(message);
        }
    }

    public void addError(String message) {
        errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
