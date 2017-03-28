package org.uva.hatt.taxform.evaluation;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.ComputedQuestion;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.IfThenElse;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.Visitor;
import org.uva.hatt.taxform.values.BooleanValue;
import org.uva.hatt.taxform.values.IntegerValue;
import org.uva.hatt.taxform.values.StringValue;
import org.uva.hatt.taxform.values.Value;

public class Evaluator implements Visitor<Value>{

    private final EnvironmentsTable environmentsTable;

    public Evaluator(EnvironmentsTable environmentsTable) {
        this.environmentsTable = environmentsTable;
    }

    @Override
    public Value visit(Form node) {
        return null;
    }

    @Override
    public Value visit(Question node) {
        return null;
    }

    @Override
    public Value visit(ComputedQuestion node) {
        return null;
    }

    @Override
    public Value visit(IfThen node) {
        return null;
    }

    @Override
    public Value visit(IfThenElse node) {
        return null;
    }

    @Override
    public Value visit(Boolean node) {
        return null;
    }

    @Override
    public Value visit(Integer node) {
        return null;
    }

    @Override
    public Value visit(Money node) {
        return null;
    }

    @Override
    public Value visit(String node) {
        return null;
    }

    @Override
    public Value visit(ValueType node) {
        return null;
    }

    @Override
    public Value visit(BooleanExpression node) {
        return node.accept(this);
    }

    @Override
    public Value visit(GroupedExpression node) {
        return node.getExpression().accept(this);
    }

    @Override
    public Value visit(Identifier identifier) {
        return environmentsTable.find(identifier.getValue());
    }

    @Override
    public Value visit(StringerLiteral stringerLiteral) {
        return new StringValue(stringerLiteral.getValue());
    }

    @Override
    public Value visit(IntegerLiteral integerLiteral) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public Value visit(BooleanLiteral booleanLiteral) {
        return new BooleanValue(booleanLiteral.isValue());
    }

    @Override
    public Value visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public Value visit(Addition addition) {
        return addition.getLhs().accept(this).add(addition.getRhs().accept(this));
    }

    @Override
    public Value visit(Division division) {
        return division.getLhs().accept(this).divide(division.getRhs().accept(this));
    }

    @Override
    public Value visit(Equal equal) {
        return equal.getLhs().accept(this).equal(equal.getRhs().accept(this));
    }

    @Override
    public Value visit(GreaterThan greaterThan) {
        return greaterThan.getLhs().accept(this).greaterThan(greaterThan.getRhs().accept(this));
    }

    @Override
    public Value visit(GreaterThanOrEqual greaterThanOrEqual) {
        return greaterThanOrEqual.getLhs().accept(this).greaterThanOrEqual(greaterThanOrEqual.getRhs().accept(this));
    }

    @Override
    public Value visit(LessThan lessThan) {
        return lessThan.getLhs().accept(this).lessThan(lessThan.getRhs().accept(this));
    }

    @Override
    public Value visit(LessThanOrEqual lessThanOrEqual) {
        return lessThanOrEqual.getLhs().accept(this).lessThanOrEqual(lessThanOrEqual.getRhs().accept(this));
    }

    @Override
    public Value visit(LogicalAnd logicalAnd) {
        return logicalAnd.getLhs().accept(this).and(logicalAnd.getRhs().accept(this));
    }

    @Override
    public Value visit(LogicalOr logicalOr) {
        return logicalOr.getLhs().accept(this).or(logicalOr.getRhs().accept(this));
    }

    @Override
    public Value visit(Multiplication multiplication) {
        return multiplication.getLhs().accept(this).multiply(multiplication.getRhs().accept(this));
    }

    @Override
    public Value visit(NotEqual notEqual) {
        return notEqual.getLhs().accept(this).notEqual(notEqual.getRhs().accept(this));
    }

    @Override
    public Value visit(Subtraction subtraction) {
        return subtraction.getLhs().accept(this).subtract(subtraction.getRhs().accept(this));
    }
}
