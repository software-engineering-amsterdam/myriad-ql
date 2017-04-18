package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;

public interface ExpressionVisitor<T> {
    T visit(Addition addition);

    T visit(Division division);

    T visit(Equal equal);

    T visit(GreaterThan greaterThan);

    T visit(GreaterThanOrEqual greaterThanOrEqual);

    T visit(LessThan lessThan);

    T visit(LessThanOrEqual lessThanOrEqual);

    T visit(LogicalAnd logicalAnd);

    T visit(LogicalOr logicalOr);

    T visit(Multiplication multiplication);

    T visit(NotEqual notEqual);

    T visit(Subtraction subtraction);

    T visit(GroupedExpression groupedExpression);

    T visit(Expression expression);

    T visit(BooleanLiteral booleanLiteral);

    T visit(Identifier identifier);

    T visit(IntegerLiteral integerLiteral);

    T visit(StringerLiteral stringerLiteral);

}
