package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
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

import java.util.HashMap;
import java.util.List;

/**
 * Created by matt on 13/03/2017.
 */
public class QLReferenceChecker implements QLVisitor<Parameter> {

    HashMap<String, List<Parameter>> expressionMap;

    public QLReferenceChecker() {
        this.expressionMap = new HashMap<>();
    }

    public void checkForCircularReferences(Form form) {

        for(Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    @Override
    public Parameter visit(Question question, String context) {
        return null;
    }

    @Override
    public Parameter visit(IfStatement ifStatement, String context) {

        for(Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Parameter visit(IfElseStatement ifElseStatement, String context) {

        for(Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        for(Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Parameter visit(CalculatedQuestion calculatedQuestion, String context) {

        calculatedQuestion.getCalculation().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Parameter parameter, String context) {
        return parameter;
    }

    @Override
    public Parameter visit(ParameterGroup parameterGroup, String context) {
        return parameterGroup.getExpression().accept(this, null);
    }

    @Override
    public Parameter visit(Addition addition, String context) {
        addition.getLeft().accept(this, null);
        addition.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Division division, String context) {
        division.getLeft().accept(this, null);
        division.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Equal equal, String context) {
        equal.getLeft().accept(this, null);
        equal.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(GreaterThan greaterThan, String context) {
        greaterThan.getLeft().accept(this, null);
        greaterThan.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        greaterThanEqualTo.getLeft().accept(this, null);
        greaterThanEqualTo.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(LessThan lessThan, String context) {
        lessThan.getLeft().accept(this, null);
        lessThan.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(LessThanEqualTo lessThanEqualTo, String context) {
        lessThanEqualTo.getLeft().accept(this, null);
        lessThanEqualTo.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(LogicalAnd logicalAnd, String context) {
        logicalAnd.getLeft().accept(this, null);
        logicalAnd.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(LogicalOr logicalOr, String context) {
        logicalOr.getLeft().accept(this, null);
        logicalOr.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, null);
        multiplication.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, null);
        notEqual.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, null);
        subtraction.getRight().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(Negation negation, String context) {
        negation.getExpression().accept(this, null);
        return null;
    }

    @Override
    public Parameter visit(BooleanType booleanType, String context) {
        return null;
    }

    @Override
    public Parameter visit(IntegerType integerType, String context) {
        return null;
    }

    @Override
    public Parameter visit(StringType stringType, String context) {
        return null;
    }
}
