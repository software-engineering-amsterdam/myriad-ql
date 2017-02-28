/**
 * Expression.java.
 */

package ql.astnodes.expressions;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.visitors.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
