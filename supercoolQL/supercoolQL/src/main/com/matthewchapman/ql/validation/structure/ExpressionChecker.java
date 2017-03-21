package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.errorhandling.ErrorLogger;
import com.matthewchapman.ql.validation.type.TypeTable;
import com.matthewchapman.ql.visitors.ExpressionVisitor;
import com.matthewchapman.ql.visitors.StatementVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 16/03/2017.
 * <p>
 * Exists to ensure that all expressions reference parameters correctly
 */
public class ExpressionChecker implements StatementVisitor<Void, String>, ExpressionVisitor<Void, String> {

    private TypeTable typeTable;
    private ErrorLogger logger;
    private Map<String, List<Parameter>> expressionMap;

    public ErrorLogger checkExpressions(Form form, TypeTable typeTable) {
        this.typeTable = typeTable;
        this.logger = new ErrorLogger();
        this.expressionMap = new HashMap<>();

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        checkForMissingParameters(typeTable);

        return logger;
    }

    public Map<String, List<Parameter>> getExpressionMap() {
        return this.expressionMap;
    }

    private void checkForMissingParameters(TypeTable typeTable) {
        for (HashMap.Entry<String, List<Parameter>> entry : expressionMap.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                if (!typeTable.containsEntry(parameter.getID())) {
                    logger.addError(parameter.getLine(), parameter.getColumn(), parameter.getID(), "Referenced parameter does not exist");
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
    public Void visit(Question question, String context) {
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {

        ifStatement.getCondition().accept(this, "If Condition");
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {

        ifElseStatement.getCondition().accept(this, "If Condition");
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
        //handle if statements
        if (!typeTable.containsEntry(parameter.getID())) {
            logger.addError(parameter.getLine(), parameter.getColumn(), parameter.getID(), "Referenced parameter does not exist");
            return null;
        }

        //handle calculated questions
        if (expressionMap.containsKey(context)) {
            expressionMap.get(context).add(parameter);
        } else {
            assert context != null;
        }
        return null;

    }
}
