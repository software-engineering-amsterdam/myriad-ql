package org.ql.typechecker.messages;

import org.ql.ast.Node;
import org.ql.ast.statement.Question;

public class TypeMismatch implements Message {
    private final Question question;

    public TypeMismatch(Question question) {
        this.question = question;
    }

    @Override
    public Node getNode() {
        return question;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
