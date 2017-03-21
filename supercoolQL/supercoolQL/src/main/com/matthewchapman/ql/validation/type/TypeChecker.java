package com.matthewchapman.ql.validation.type;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.ErrorType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
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
import com.matthewchapman.ql.app.ErrorLogger;
import com.matthewchapman.ql.visitors.ExpressionVisitor;
import com.matthewchapman.ql.visitors.StatementVisitor;
import com.matthewchapman.ql.visitors.TypeVisitor;

/**
 * Created by matt on 03/03/2017. 222
 * <p>
 * Visitor to check expressions for validity (circular dependency, types, etc).
 */
public class TypeChecker implements StatementVisitor<Type, String>, ExpressionVisitor<Type, String>, TypeVisitor<Type, String> {

    private TypeTable typeTable;
    private final ErrorLogger logger;
    private static final String INCOMPATIBLE_TYPE = "Incompatible parameter type in use";
    private static final String NON_BOOLEAN = "Non-boolean parameter in use";
    private static final String BOOLEAN = "boolean";

    public TypeChecker() {
        logger = new ErrorLogger();
    }

    public ErrorLogger checkExpressionTypes(Form form, TypeTable typeTable) {
        this.typeTable = typeTable;

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        return logger;
    }

    private Type verifyTypeCorrectness(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!left.isCompatible(right)) {
            logger.addError(operation.getLine(), operation.getColumn(), "Binary Operation", INCOMPATIBLE_TYPE);
            return new ErrorType();
        }

        return left;
    }

    private Type verifyBooleanExpression(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!BOOLEAN.equals(left.toString()) || !BOOLEAN.equals(right.toString())) {
            logger.addError(operation.getLine(), operation.getColumn(), "Boolean Expression", NON_BOOLEAN);
            return new ErrorType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(Question question, String context) {
        return null;
    }

    @Override
    public Type visit(IfStatement ifStatement, String context) {
        Type type = ifStatement.getCondition().accept(this, null);

        if (!BOOLEAN.equals(type.toString())) {
            logger.addError(ifStatement.getLine(), ifStatement.getColumn(), "If Statement", NON_BOOLEAN);
            return new ErrorType();
        }
        return new BooleanType();
    }

    @Override
    public Type visit(IfElseStatement ifElseStatement, String context) {
        Type type = ifElseStatement.getCondition().accept(this, null);

        if (!BOOLEAN.equals(type.toString())) {
            logger.addError(ifElseStatement.getLine(), ifElseStatement.getColumn(), "If-Else Statement", NON_BOOLEAN);
            return new ErrorType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(CalculatedQuestion calculatedQuestion, String context) {
        Type calculationType = calculatedQuestion.getCalculation().accept(this, null);
        if (!calculatedQuestion.getType().isCompatible(calculationType)) {
            logger.addError(calculatedQuestion.getLine(), calculatedQuestion.getColumn(), calculatedQuestion.getName(), INCOMPATIBLE_TYPE);
        }

        return calculatedQuestion.getType();
    }

    @Override
    public Type visit(StringLiteral stringLiteral, String context) {
        return new StringType();
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral, String context) {
        return new IntegerType();
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral, String context) {
        return new BooleanType();
    }

    @Override
    public Type visit(Parameter parameter, String context) {
        Type type = typeTable.getEntryByName(parameter.getID());
        assert type != null;  //something very wrong if this happens
        return type;
    }

    @Override
    public Type visit(ParameterGroup parameterGroup, String context) {
        return parameterGroup.getExpression().accept(this, null);
    }

    @Override
    public Type visit(Addition addition, String context) {
        return verifyTypeCorrectness(addition);
    }

    @Override
    public Type visit(Subtraction subtraction, String context) {
        return verifyTypeCorrectness(subtraction);
    }

    @Override
    public Type visit(Multiplication multiplication, String context) {
        return verifyTypeCorrectness(multiplication);
    }

    @Override
    public Type visit(Division division, String context) {
        return verifyTypeCorrectness(division);
    }

    @Override
    public Type visit(Equal equal, String context) {
        return verifyTypeCorrectness(equal);
    }

    @Override
    public Type visit(NotEqual notEqual, String context) {
        return verifyTypeCorrectness(notEqual);
    }

    @Override
    public Type visit(GreaterThan greaterThan, String context) {
        return verifyTypeCorrectness(greaterThan);
    }

    @Override
    public Type visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return verifyTypeCorrectness(greaterThanEqualTo);
    }

    @Override
    public Type visit(LessThan lessThan, String context) {
        return verifyTypeCorrectness(lessThan);
    }

    @Override
    public Type visit(LessThanEqualTo lessThanEqualTo, String context) {
        return verifyTypeCorrectness(lessThanEqualTo);
    }

    @Override
    public Type visit(LogicalAnd logicalAnd, String context) {
        return verifyBooleanExpression(logicalAnd);
    }

    @Override
    public Type visit(LogicalOr logicalOr, String context) {
        return verifyBooleanExpression(logicalOr);
    }

    @Override
    public Type visit(Negation negation, String context) {
        Type type = negation.getExpression().accept(this, null);

        if (!BOOLEAN.equals(type.toString())) {
            logger.addError(negation.getLine(), negation.getColumn(), "Negation", NON_BOOLEAN);
            return new ErrorType();
        }

        return type;
    }

    @Override
    public Type visit(BooleanType booleanType, String context) {
        return booleanType;
    }

    @Override
    public Type visit(StringType stringType, String context) {
        return stringType;
    }

    @Override
    public Type visit(ErrorType errorType, String context) {
        return errorType;
    }

    @Override
    public Type visit(IntegerType integerType, String context) {
        return integerType;
    }

}
