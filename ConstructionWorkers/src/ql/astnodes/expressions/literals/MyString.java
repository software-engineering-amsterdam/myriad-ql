/**
 * MyString.java.
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.types.Type;
import ql.astnodes.LineNumber;
import ql.astnodes.types.StringType;
import ql.astnodes.visitors.ExpressionVisitor;

public class MyString extends Literal {

    private final Type type;
    private final String value;

    public MyString(String value, LineNumber lineNumber) {
        super(lineNumber);
        this.value = value;
        this.type = new StringType(lineNumber);
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
