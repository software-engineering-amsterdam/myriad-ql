package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.atomic.*;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 13/03/2017.
 *
 * Provides type checking and missing parameter checking
 */
public class QLStructureChecker implements QLVisitor<Void> {

    HashMap<String, List<Parameter>> expressionMap;

    public QLStructureChecker() {
        this.expressionMap = new HashMap<>();
    }

    public void checkQLStructure(Form form, Map<String, Type> typeTable) {

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        for (HashMap.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for(Parameter parameter : entry.getValue()) {
                if(!typeTable.containsKey(parameter.getID())) {
                    System.err.println("something is missing: " + parameter.getID());    //TODO: Proper error
                }
            }
        }

    }


    @Override
    public Void visit(Question question, String context) {
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {

        for(Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this, context);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {

        for(Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        for(Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {

        if(!expressionMap.containsKey(calculatedQuestion.getName())) {
            expressionMap.put(calculatedQuestion.getName(), new ArrayList<>());
        }

        calculatedQuestion.getCalculation().accept(this, calculatedQuestion.getName());
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        expressionMap.get(context).add(parameter);
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        parameterGroup.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        addition.getLeft().accept(this, context);
        addition.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        division.getLeft().accept(this, context);
        division.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        equal.getLeft().accept(this, context);
        equal.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        greaterThan.getLeft().accept(this, context);
        greaterThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        greaterThanEqualTo.getLeft().accept(this, context);
        greaterThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        lessThan.getLeft().accept(this, context);
        lessThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        lessThanEqualTo.getLeft().accept(this, context);
        lessThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd, String context) {
        logicalAnd.getLeft().accept(this, context);
        logicalAnd.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr, String context) {
        logicalOr.getLeft().accept(this, context);
        logicalOr.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, context);
        multiplication.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, context);
        notEqual.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, context);
        subtraction.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Negation negation, String context) {
        negation.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        return null;
    }
}
