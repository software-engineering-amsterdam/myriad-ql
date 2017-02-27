package org.ql.typechecker.messages;

import org.ql.ast.Node;
import org.ql.typechecker.expression.TypeError;

import java.util.List;

public interface MessageBag {
    void addError(TypeError error);

    void addError(String message, Node node);

    void addError(String message);

    void addErrors(List<String> errors);

    List<String> getErrors();

    MessageBag merge(MessageBag anotherBag);

    boolean hasErrors();
}
