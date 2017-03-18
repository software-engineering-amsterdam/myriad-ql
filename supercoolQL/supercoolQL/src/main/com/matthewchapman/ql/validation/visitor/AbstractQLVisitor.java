package com.matthewchapman.ql.validation.visitor;

import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 13/03/2017.
 * <p>
 * Provides default visitor implementation to avoid having to implement everything in every visitor
 */
public abstract class AbstractQLVisitor<T, C> implements QLVisitor<T, C> {
    @Override
    public T visit(Question question, C context) {
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement, C context) {
        return null;
    }

    @Override
    public T visit(IfElseStatement ifElseStatement, C context) {
        return null;
    }

    @Override
    public T visit(CalculatedQuestion calculatedQuestion, C context) {
        return null;
    }

    @Override
    public T visit(Parameter parameter, C context) {
        return null;
    }

    @Override
    public T visit(ParameterGroup parameterGroup, C context) {
        return null;
    }

    @Override
    public T visit(StringLiteral stringLiteral, C context) {
        return null;
    }

    @Override
    public T visit(IntegerLiteral integerLiteral, C context) {
        return null;
    }

    @Override
    public T visit(BooleanLiteral booleanLiteral, C context) {
        return null;
    }

    @Override
    public T visit(Addition addition, C context) {
        return null;
    }

    @Override
    public T visit(Division division, C context) {
        return null;
    }

    @Override
    public T visit(Equal equal, C context) {
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan, C context) {
        return null;
    }

    @Override
    public T visit(GreaterThanEqualTo greaterThanEqualTo, C context) {
        return null;
    }

    @Override
    public T visit(LessThan lessThan, C context) {
        return null;
    }

    @Override
    public T visit(LessThanEqualTo lessThanEqualTo, C context) {
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd, C context) {
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr, C context) {
        return null;
    }

    @Override
    public T visit(Multiplication multiplication, C context) {
        return null;
    }

    @Override
    public T visit(NotEqual notEqual, C context) {
        return null;
    }

    @Override
    public T visit(Subtraction subtraction, C context) {
        return null;
    }

    @Override
    public T visit(Negation negation, C context) {
        return null;
    }

    @Override
    public T visit(BooleanType booleanType, C context) {
        return null;
    }

    @Override
    public T visit(IntegerType integerType, C context) {
        return null;
    }

    @Override
    public T visit(StringType stringType, C context) {
        return null;
    }
}
