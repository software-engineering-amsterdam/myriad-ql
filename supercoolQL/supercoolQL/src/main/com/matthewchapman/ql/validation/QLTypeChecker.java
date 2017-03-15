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

import java.util.Map;

/**
 * Created by matt on 03/03/2017. 222
 * <p>
 * Visitor to check expressions for validity (circular dependency, types, etc).
 */
public class QLTypeChecker extends AbstractQLVisitor<Type> {

    private Map<String, Type> typeTable;

    public void checkExpressionTypes(Form form, Map<String, Type> typeTable) {
        this.typeTable = typeTable;

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    private Type verifyTypeCorrectness(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!left.isCompatible(right)) {
            System.err.println("incompatible types");   //TODO proper error
            return new ErrorType();
        }

        return left;
    }

    private Type verifyBooleanExpression(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!left.toString().equals("boolean") || !right.toString().equals("boolean")) {
            System.err.println("Incorrect boolean expression");     //TODO proper error
            return new ErrorType();
        }

        return new BooleanType();
    }

    @Override
    public Type visit(IfStatement ifStatement, String context) {
        ifStatement.getCondition().accept(this, null);
        return new BooleanType();
    }

    @Override
    public Type visit(IfElseStatement ifElseStatement, String context) {
        ifElseStatement.getCondition().accept(this, null);
        return new BooleanType();
    }

    @Override
    public Type visit(CalculatedQuestion calculatedQuestion, String context) {
        Type calculationType = calculatedQuestion.getCalculation().accept(this, null);
        if (!calculatedQuestion.getType().isCompatible(calculationType)) {
            System.err.println("Incorrect expression");     //TODO proper error
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
        return typeTable.get(parameter.getID());
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

        if (!type.toString().equals("boolean")) {
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
    public Type visit(IntegerType integerType, String context) {
        return integerType;
    }

}
