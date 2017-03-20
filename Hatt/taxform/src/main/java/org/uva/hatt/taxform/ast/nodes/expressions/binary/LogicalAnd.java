package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

public class LogicalAnd extends BooleanExpression {
    public LogicalAnd(int lineNumber, Expression lhs, Expression rhs) {
        super(lineNumber, lhs, rhs);
    }
}
