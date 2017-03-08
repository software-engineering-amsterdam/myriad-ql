package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.atomic.BooleanType;
import com.matthewchapman.ql.ast.atomic.IntegerType;
import com.matthewchapman.ql.ast.atomic.StringType;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.binary.Addition;
import com.matthewchapman.ql.ast.expression.binary.BinaryOperation;
import com.matthewchapman.ql.ast.expression.binary.Subtraction;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 03/03/2017. 222
 *
 * Visitor to check expressions for validity (circular dependency, types, etc).
 */
public class ExpressionChecker extends AbstractQLVisitor<Type> {

    private List<Parameter> parameters;
    Map<String, Type> typeTable;

    public void checkExpression(Statement statement, Map<String, Type> typeTable) {

        parameters = new ArrayList<>();
        statement.accept(this);
        this.typeTable = typeTable;

    }

    @Override
    public Type visit(IfStatement ifStatement) {
        ifStatement.getCondition().accept(this);
        return null;
    }

    @Override
    public Type visit(IfElseStatement ifElseStatement) {
        ifElseStatement.getCondition().accept(this);
        return null;
    }

    @Override
    public Type visit(CalculatedQuestion calculatedQuestion) {
        Type calculationType = calculatedQuestion.getCalculation().accept(this);
        if(!calculatedQuestion.getType().isCompatible(calculationType)) {
            System.err.println("HOLY SHIT IT WORKS");
        }

        return null;
    }

    @Override
    public Type visit(Parameter parameter) {
        System.out.println("parameter: " + parameter.getID());
        parameters.add(parameter);
        return typeTable.get(parameter.getID());
    }

    @Override
    public Type visit(Addition addition) {
        return verifyTypeCorrectness(addition);
//        addition.getLeft().accept(this);
//        addition.getRight().accept(this);

    }

    @Override
    public Type visit(Subtraction subtraction) {
        return verifyTypeCorrectness(subtraction);
//        subtraction.getLeft().accept(this);
//        subtraction.getRight().accept(this);

    }

    private Type verifyTypeCorrectness(BinaryOperation operation) {
        Type leftType = operation.getLeft().accept(this);
        Type rightType = operation.getRight().accept(this);

        return leftType;
    }

    @Override
    public Type visit(BooleanType booleanType) {
        return new BooleanType();
    }

    @Override
    public Type visit(StringType stringType) {
        return new StringType();
    }

    @Override
    public Type visit(IntegerType integerType) {
        return new IntegerType();
    }
}
