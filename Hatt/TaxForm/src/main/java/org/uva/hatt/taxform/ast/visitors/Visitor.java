package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface Visitor<T> {
    T visit(Form node);
    T visit(Question node);
    T visit(ComputedQuestion node);
    T visit(IfThen node);
    T visit(IfThenElse node);
    T visit(Boolean node);
    T visit(Integer node);
    T visit(Money node);
    T visit(String node);
    T visit(ValueType node);
    T visit(BooleanExpression node);
    T visit(GroupedExpression node);
    T visit(Identifier identifier);
    T visit(StringerLiteral stringerLiteral);
    T visit(IntegerLiteral integerLiteral);
    T visit(BooleanLiteral booleanLiteral);
    T visit(Expression expression);
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
}
