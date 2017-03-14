/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/TypeChecker.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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
import ql.visitorinterfaces.ExpressionVisitor;
import ql.visitorinterfaces.FormAndStatementVisitor;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.qlerrors.CyclicDependencyError;
import ql.semanticchecker.messagehandling.errors.qlerrors.InvalidTypeError;
import ql.semanticchecker.messagehandling.errors.qlerrors.UndefinedQuestionError;

import java.util.*;

public class TypeChecker implements FormAndStatementVisitor<Void>, ExpressionVisitor<Type> {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private Identifier tempIdentifierLiteral;

    private Map<String, List<String>> dependencyList = new HashMap<>();

    public TypeChecker(Form ast, Map<String, Type> identifierToTypeMap, MessageData messages) {
        this.identifierToTypeMap = identifierToTypeMap;
        this.messages = messages;
        ast.accept(this);
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

        if (new UndefinedType().equals(statementType)) {
            messages.addError(new InvalidTypeError(statement.getLineNumber(), new BooleanType()));
        }

        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }

    private Type getTypeIfStatement(Type expressionType) {
        if (new BooleanType().equals(expressionType)) {
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

        if (!(statement.getType()).equals(expressionType)) {
            messages.addError(new InvalidTypeError(statement.getLineNumber(), statement.getType()));
        }
        return null;
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
        if (isEqual(leftExpressionType, rightExpressionType) && new BooleanType().equals(leftExpressionType)) {
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
        if (isEqual(leftExpressionType,rightExpressionType) &&  (new MoneyType().equals(leftExpressionType) ||
                new IntegerType().equals(leftExpressionType))) {
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
        if (new BooleanType().equals(expressionType)) {
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
        if (new IntegerType().equals(expressionType) || new MoneyType().equals(expressionType) ||
                new BooleanType().equals(expressionType) || new StringType().equals(expressionType)) {
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
        if (new IntegerType().equals(expressionType) || new MoneyType().equals(expressionType)) {
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
                (new MoneyType().equals(leftExpressionType) || new IntegerType().equals(leftExpressionType) ||
                        new StringType().equals(leftExpressionType))) {
            return new BooleanType();
        }
        return new UndefinedType();
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

    // Cyclic dependency functions.

    // Checks for cyclic dependencies for the specified identifier.
    private void checkCyclicDependency(Identifier identifier) {
        List<String> toDependencies = getDependencyNames(identifier.getName());
        boolean revertedDependencyExists = toDependencies.contains(tempIdentifierLiteral.getName());

        if (revertedDependencyExists) {
            messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLineNumber(), tempIdentifierLiteral, identifier));
        }
        updateDependencyData(identifier.getName());
    }

    private void updateDependencyData(String identifierName) {
        List<String> identifiersOfAffectedNodes = getIdentifiersWithDependency();

        for (String newDependency : identifiersOfAffectedNodes) {
            addDependency(newDependency, identifierName);
        }

        if (dependencyList.get(identifierName) != null) {
            for (String newDependency : dependencyList.get(identifierName)) {
                addDependency(tempIdentifierLiteral.getName(), newDependency);
            }
        }
    }

    // Returns a list with all the identifier names that have a dependency with 'tempIdentifierLiteral', including
    // 'tempIdentifierLiteral'.
    private List<String> getIdentifiersWithDependency() {
        List<String> newDependencyIdentifiers = new ArrayList<>();

        for (String key : dependencyList.keySet()) {
            List<String> dependenciesForKey = getDependencyNames(key);

            if (dependenciesForKey.contains(tempIdentifierLiteral.getName())) {
                newDependencyIdentifiers.add(key);
            }
        }

        newDependencyIdentifiers.add(tempIdentifierLiteral.getName());
        return newDependencyIdentifiers;
    }

    // Returns a a list with all the identifier names with which the specified identifier has a dependency.
    private List<String> getDependencyNames(String identifierName) {
        List<String> names = new ArrayList<>();

        if (isKey(identifierName)) {
            for (String dependency : dependencyList.get(identifierName)) {
                names.add(dependency);
            }
        }
        return names;
    }

    // Adds the identifier 'dependant' to the dependency list of the identifier 'id'. If the identifier 'id' is not
    // a key in 'dependencyList', add it with a dependency list that contains the identifier 'dependant'.
    private void addDependency(String id, String dependant) {
        List<String> dependList = dependencyList.get(id);

        if (!isKey(id)) {
            dependList = new ArrayList<>();
        }
        dependList.add(dependant);
        dependencyList.put(id, dependList);
    }

    private Boolean isKey(String identifierName) {
        return dependencyList.get(identifierName) != null;
    }

    private static boolean isEqual(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }
}
