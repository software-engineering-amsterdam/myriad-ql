package org.uva.hatt.taxform.ast.nodes;

public class ValueType extends ASTNode{

    private String valueOfType;

    public ValueType(int lineNumber, String valueOfType) {
        super(lineNumber);
        this.valueOfType = valueOfType;
    }

    public String getValueOfType() {
        return valueOfType;
    }
}
