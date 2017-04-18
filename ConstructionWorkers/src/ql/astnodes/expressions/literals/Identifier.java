/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/literals/Identifier.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.ExpressionVisitor;

public class Identifier extends Literal {

    private final String name;

    public Identifier(String name, LineNumber lineNumber) {
        super(lineNumber);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
