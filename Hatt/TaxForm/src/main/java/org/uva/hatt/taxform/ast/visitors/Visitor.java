package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.ComputationExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface Visitor {
    Form visit(Form node);
    FormId visit(FormId node);
    Question visit(Question node);
    Conditional visit(Conditional node);
    Boolean visit(Boolean node);
    Integer visit(Integer node);
    Money visit(Money node);
    String visit(String node);
    ValueType visit(ValueType node);
    BooleanExpression visit(BooleanExpression node);
    ComputationExpression visit(ComputationExpression node);
    GroupedExpression visit(GroupedExpression node);
    ValueType visit(Identifier identifier);
    StringerLiteral visit(StringerLiteral stringerLiteral);
    IntegerLiteral visit(IntegerLiteral integerLiteral);
    BooleanLiteral visit(BooleanLiteral booleanLiteral);
    Expression visit(Expression expression);
}
