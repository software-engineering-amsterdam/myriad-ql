package org.ql.typechecker.messages;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class DuplicateVariableMessage implements Message {

    private final Identifier erroneousVariable;

    public DuplicateVariableMessage(Identifier erroneousVariable) {
        this.erroneousVariable = erroneousVariable;
    }

    @Override
    public String getMessage() {
        return "Duplicated parameter '" + erroneousVariable + "'";
    }

    @Override
    public Node getNode() {
        return erroneousVariable;
    }
}
