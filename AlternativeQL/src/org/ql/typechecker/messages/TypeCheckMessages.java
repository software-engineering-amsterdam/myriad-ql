package org.ql.typechecker.messages;

import org.ql.ast.SourceLocation;
import org.ql.ast.Node;
import org.ql.typechecker.expression.TypeError;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckMessages implements MessageBag {

    private List<String> errors = new ArrayList<>();

    @Override
    public void addError(TypeError error) {
        addError(error.getMessage(), error.getNode());
    }

    @Override
    public void addError(String message, Node node) {
        SourceLocation sourceLocation = node.getSourceLocation();

        if (sourceLocation != null) {
            addError(message + " on line " + sourceLocation.getLine() + ":" + sourceLocation.getColumn());
        } else {
            addError(message);
        }
    }

    @Override
    public void addError(String message) {
        errors.add(message);
    }

    @Override
    public void addErrors(List<String> errors) {
        for (String error : errors) {
            addError(error);
        }
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
