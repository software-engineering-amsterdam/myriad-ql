package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.validation.visitor.AbstractQLVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 13/03/2017.
 * <p>
 * Provides type checking and missing parameter checking
 */
public class QLStructureChecker extends AbstractQLVisitor<Void> {

    private final HashMap<String, List<Parameter>> expressionMap;
    private final QLErrorLogger logger;

    public QLStructureChecker() {
        this.expressionMap = new HashMap<>();
        this.logger = new QLErrorLogger();
    }

    public QLErrorLogger checkQLStructure(Form form, Map<String, Type> typeTable) {

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        checkForMissingParameters(typeTable);
        checkForCircularDependencies();

        return this.logger;
    }

    private void checkForMissingParameters(Map<String, Type> typeTable) {
        for (HashMap.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                if (!typeTable.containsKey(parameter.getID())) {
                    logger.addError(parameter.getLine(), parameter.getColumn(), parameter.getID(), "Non-existing parameter referenced");
                }
            }
        }
    }

    //TODO it works, but it's not nice.
    private void checkForCircularDependencies() {

        for (HashMap.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {

            List<Parameter> parameters = new ArrayList<>(entry.getValue());

            for (Parameter parameter : parameters) {
                if (expressionMap.containsKey(parameter.getID())) {
                    expressionMap.get(parameter.getID()).addAll(parameters);

                    if (expressionMap.get(parameter.getID()).contains(parameter.getID())) {
                        //System.err.println(expressionMap.);
                        break;
                    }

                }
            }
        }
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {

        if (!expressionMap.containsKey(calculatedQuestion.getName())) {
            expressionMap.put(calculatedQuestion.getName(), new ArrayList<>());
        }

        calculatedQuestion.getCalculation().accept(this, calculatedQuestion.getName());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {

        for (Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this, context);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {

        for (Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        for (Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        parameterGroup.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        addition.getLeft().accept(this, context);
        addition.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, context);
        subtraction.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, context);
        multiplication.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        division.getLeft().accept(this, context);
        division.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, context);
        notEqual.getRight().accept(this, context);
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
    public Void visit(Negation negation, String context) {
        negation.getExpression().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        expressionMap.get(context).add(parameter);
        return null;
    }

}
