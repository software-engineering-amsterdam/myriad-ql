package org.ql.typechecker.messages;

import org.ql.ast.Metadata;
import org.ql.ast.Node;
import org.ql.typechecker.expression.TypeError;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckMessages implements MessageBag {

    private final List<String> errors = new ArrayList<>();

    @Override
    public void addError(TypeError error) {
        addError(error.getMessage(), error.getNode());
    }

    @Override
    public void addError(String message, Node node) {
        Metadata metadata = node.getMetadata();

        if (metadata != null) {
            errors.add(message + " on line " + metadata.getLine() + ":" + metadata.getColumn());
        } else {
            errors.add(message);
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
