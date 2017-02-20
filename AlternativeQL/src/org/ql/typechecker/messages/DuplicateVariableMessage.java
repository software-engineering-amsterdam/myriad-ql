package org.ql.typechecker.messages;

public class DuplicateVariableMessage extends Message {

    private ErrorType errorType = ErrorType.DUPLICATE_VARIABLE;
    private String erroneousVariable;

    public DuplicateVariableMessage(String erroneousVariable) {
        this.erroneousVariable = erroneousVariable;
    }

    @Override
    public String getType() {
        return errorType.name();
    }

    @Override
    public String getErroneousVariable() {
        return this.erroneousVariable;
    }
}
