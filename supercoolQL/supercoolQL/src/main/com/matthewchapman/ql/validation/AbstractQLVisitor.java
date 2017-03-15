package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.atomic.*;
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
 *
 * Provides default visitor implementation to avoid having to implement everything in every visitor
 */
public abstract class AbstractQLVisitor<T> implements QLVisitor<T> {
    @Override
    public T visit(Question question, String context) {
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement, String context) {
        return null;
    }

    @Override
    public T visit(IfElseStatement ifElseStatement, String context) {
        return null;
    }

    @Override
    public T visit(CalculatedQuestion calculatedQuestion, String context) {
        return null;
    }

    @Override
    public T visit(Parameter parameter, String context) {
        return null;
    }

    @Override
    public T visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public T visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public T visit(IntegerLiteral integerLiteral, String context) {
        return null;
    }

    @Override
    public T visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public T visit(Addition addition, String context) {
        return null;
    }

    @Override
    public T visit(Division division, String context) {
        return null;
    }

    @Override
    public T visit(Equal equal, String context) {
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public T visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public T visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public T visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd, String context) {
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr, String context) {
        return null;
    }

    @Override
    public T visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public T visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public T visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public T visit(Negation negation, String context) {
        return null;
    }

    @Override
    public T visit(BooleanType booleanType, String context) {
        return null;
    }

    @Override
    public T visit(IntegerType integerType, String context) {
        return null;
    }

    @Override
    public T visit(StringType stringType, String context) {
        return null;
    }
}
