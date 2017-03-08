package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.atomic.BooleanType;
import com.matthewchapman.ql.ast.atomic.IntegerType;
import com.matthewchapman.ql.ast.atomic.StringType;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 02/03/2017.
 *
 * Provides base implementation for QLVisitor - avoids having to have full implementations in
 * concrete classes
 */
public abstract class AbstractQLVisitor<T> implements QLVisitor<T> {

    @Override
    public T visit(Question question) {
        return null;
    }

    @Override
    public T visit(BooleanType booleanType) {
        return null;
    }

    @Override
    public T visit(IntegerType integerType) {
        return null;
    }

    @Override
    public T visit(StringType stringType) {
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement) {
        return null;
    }

    @Override
    public T visit(CalculatedQuestion calculatedQuestion) {
        return null;
    }

    @Override
    public T visit(IfElseStatement ifElseStatement) {
        return null;
    }

    @Override
    public T visit(Parameter parameter) {
        return null;
    }

    @Override
    public T visit(ParameterGroup parameterGroup) {
        return null;
    }

    @Override
    public T visit(Addition addition) {
        return null;
    }

    @Override
    public T visit(Division division) {
        return null;
    }

    @Override
    public T visit(Equal equal) {
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public T visit(GreaterThanEqualTo greaterThanEqualTo) {
        return null;
    }

    @Override
    public T visit(LessThan lessThan) {
        return null;
    }

    @Override
    public T visit(LessThanEqualTo lessThanEqualTo) {
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public T visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public T visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public T visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public T visit(Negation negation) {
        return null;
    }
}
