/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/literals/MyInteger.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.types.Type;
import ql.astnodes.LineNumber;
import ql.astnodes.types.IntegerType;
import ql.visitorinterfaces.ExpressionVisitor;

public class MyInteger extends Literal {

    private final Type type;
    private final Integer value;

    public MyInteger(Integer value, LineNumber lineNumber) {
        super(lineNumber);
        this.type = new IntegerType(lineNumber);
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
