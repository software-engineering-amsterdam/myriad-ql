/**
 * TypeChecker.java.
 */

package ql.semanticchecker;

import ql.astnodes.Form;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.*;
import ql.astnodes.expressions.binaries.numerical.*;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.*;
import ql.astnodes.statements.*;
import ql.astnodes.types.*;
import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.CyclicDependencyError;
import ql.semanticchecker.messagehandling.errors.InvalidTypeError;
import ql.semanticchecker.messagehandling.errors.UndefinedQuestionError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeChecker implements FormAndStatementVisitor<Void>, ExpressionVisitor<Type> {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private final DependencyData dependencyList;
    private Identifier tempIdentifierLiteral;

    public TypeChecker(Form ast, Map<String, Type> identifierToTypeMap, MessageData messages) {
        this.identifierToTypeMap = identifierToTypeMap;
        this.messages = messages;
        this.dependencyList = new DependencyData();
        ast.accept(this);
    }

    @Override
    public Type visit(AND expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeLogic(left, right);
    }

    @Override
    public Type visit(OR expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeLogic(left, right);
    }

    private Type getTypeLogic(Type leftExpressionType, Type rightExpressionType) {

        if (isEqual(leftExpressionType, rightExpressionType) &&
                isEqual(leftExpressionType, new BooleanType())) {
            return leftExpressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Type visit(Addition expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeNumerical(left, right);
    }

    @Override
    public Type visit(Division expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeNumerical(left, right);
    }

    @Override
    public Type visit(Multiplication expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeNumerical(left, right);
    }

    @Override
    public Type visit(Subtraction expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeNumerical(left, right);
    }

    private Type getTypeNumerical(Type leftExpressionType, Type rightExpressionType) {

        if (isEqual(leftExpressionType,rightExpressionType)
                && (isEqual(leftExpressionType, new MoneyType()) ||
                isEqual(leftExpressionType, new IntegerType()))) {
            return leftExpressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Type visit(Negation expression) {
        Type type = expression.getExpression().accept(this);
        return getTypeNegation(type);
    }

    private Type getTypeNegation(Type expressionType) {

        if (expressionType.equals(new BooleanType())) {
            return expressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Type visit(Parentheses expression) {
        Type type = expression.getExpression().accept(this);
        return getTypeParentheses(type);
    }

    private Type getTypeParentheses(Type expressionType) {

        if (isEqual(expressionType, new IntegerType()) || isEqual(expressionType, new MoneyType()) ||
                isEqual(expressionType, new BooleanType()) || isEqual(expressionType, new StringType())) {
            return expressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Type visit(Positive expression) {
        Type type = expression.getExpression().accept(this);
        return getTypePositiveNegative(type);
    }

    @Override
    public Type visit(Negative expression) {
        Type type = expression.getExpression().accept(this);
        return getTypePositiveNegative(type);
    }

    private Type getTypePositiveNegative(Type expressionType) {

        if (isEqual(expressionType, new IntegerType()) || isEqual(expressionType, new MoneyType())) {
            return expressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Type visit(EQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    @Override
    public Type visit(GT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    @Override
    public Type visit(GTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    @Override
    public Type visit(LT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    @Override
    public Type visit(LTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    @Override
    public Type visit(NEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return getTypeEquality(left, right);
    }

    private Type getTypeEquality(Type leftExpressionType, Type rightExpressionType) {

        if (isEqual(leftExpressionType,rightExpressionType) &&
                (isEqual(leftExpressionType,new MoneyType()) || isEqual(leftExpressionType, new IntegerType()) ||
                        isEqual(leftExpressionType, new StringType()))) {
            return new BooleanType();
        }

        return new UndefinedType();
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        Type expressionType = statement.getExpression().accept(this);

        Type statementType = getTypeIfStatement(expressionType);
        if (isEqual(statementType, new UndefinedType())) {
            messages.addError(new InvalidTypeError(statement.getLineNumber(), new BooleanType()));
        }

        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }

    private Type getTypeIfStatement(Type expressionType) {

        if (isEqual(expressionType, new BooleanType())) {
            return expressionType;
        }

        return new UndefinedType();
    }

    @Override
    public Void visit(SimpleQuestion statement) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion statement) {
        tempIdentifierLiteral = statement.getIdentifier();
        Type expressionType = statement.getExpression().accept(this);
        tempIdentifierLiteral = null;

        if (!isEqual(expressionType, statement.getType())) {
            messages.addError(new InvalidTypeError(statement.getLineNumber(), statement.getType()));
        }

        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        if (identifierToTypeMap.get(identifier.getName()) == null) {
            messages.addError(new UndefinedQuestionError(identifier.getLineNumber(), identifier));
        }

        if (tempIdentifierLiteral != null) {
            if (tempIdentifierLiteral.getName().equals(identifier.getName())) {
                messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLineNumber(),
                        tempIdentifierLiteral, identifier));
            } else {
                checkCyclicDependency(identifier);
            }
        }
        return identifierToTypeMap.get(identifier.getName());
    }

    private void checkCyclicDependency(Identifier start) {
        boolean revertedDependencyExists = checkRevertedDependency(start.getName());
        if (revertedDependencyExists) {
            messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLineNumber(), tempIdentifierLiteral, start));
        }
        updateDependencyData(start.getName());
    }

    private boolean checkRevertedDependency(String end) {
        List<String> toDependencies = dependencyList.getDependencyNames(end);
        return toDependencies.contains(tempIdentifierLiteral.getName());
    }

    private void updateDependencyData(String start) {
        List<String> identifiersOfAffectedNodes = getIdentifiersWithDependency();

        for (String newDependency : identifiersOfAffectedNodes) {
            dependencyList.addDependency(newDependency, start);
        }
        addDependenciesForId(dependencyList.getDependencies(start));
    }

    private List<String> getIdentifiersWithDependency() {
        List<String> newDependencyIdentifiers = new ArrayList<>();

        for (String start : dependencyList.getKeys()) {
            List<String> dependenciesForKey = dependencyList.getDependencyNames(start);

            if (dependenciesForKey.contains(tempIdentifierLiteral.getName())) {
                newDependencyIdentifiers.add(start);
            }
        }
        newDependencyIdentifiers.add(tempIdentifierLiteral.getName());
        return newDependencyIdentifiers;
    }

    private void addDependenciesForId(List<String> startIds) {
        if (startIds != null) {
            for (String newDependency : startIds) {
                dependencyList.addDependency(tempIdentifierLiteral.getName(), newDependency);
            }
        }
    }

    @Override
    public Type visit(MyBoolean literal) {
        return new BooleanType();
    }

    @Override
    public Type visit(MyInteger literal) {
        return new IntegerType();
    }

    @Override
    public Type visit(Money literal) {
        return new MoneyType();
    }

    @Override
    public Type visit(MyString literal) {
        return new StringType();
    }

    private static boolean isEqual(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }
}
