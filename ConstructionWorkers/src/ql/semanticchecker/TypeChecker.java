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
        if ( new BooleanType().equals(expressionType)) {
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

    // Cyclic Dependency functions...

    private Boolean containsKey(String id) {
        return dependencyList.get(id) != null;
    }

    private List<String> getDependencyNames(String id) {
        List<String> names = new ArrayList<>();

        if (containsKey(id)) {
            for (String dependency : dependencyList.get(id)) {
                names.add(dependency);
            }
        }
        return names;
    }

    private void addDependency(String id, String dependant) {
        List<String> dependList = dependencyList.get(id);

        if (!containsKey(id)) {
            dependList = new ArrayList<>();
        }
        dependList.add(dependant);
        dependencyList.put(id, dependList);
    }

    private void checkCyclicDependency(Identifier start) {
        List<String> toDependencies = getDependencyNames(start.getName());
        boolean revertedDependencyExists = toDependencies.contains(tempIdentifierLiteral.getName());

        if (revertedDependencyExists) {
            messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLineNumber(), tempIdentifierLiteral, start));
        }
        updateDependencyData(start.getName());
    }

    private void updateDependencyData(String start) {
        List<String> identifiersOfAffectedNodes = getIdentifiersWithDependency();

        for (String newDependency : identifiersOfAffectedNodes) {
            addDependency(newDependency, start);
        }

        if (dependencyList.get(start) != null) {
            for (String newDependency : dependencyList.get(start)) {
                addDependency(tempIdentifierLiteral.getName(), newDependency);
            }
        }
    }

    private List<String> getIdentifiersWithDependency() {
        List<String> newDependencyIdentifiers = new ArrayList<>();

        for (String start : dependencyList.keySet()) {
            List<String> dependenciesForKey = getDependencyNames(start);

            if (dependenciesForKey.contains(tempIdentifierLiteral.getName())) {
                newDependencyIdentifiers.add(start);
            }
        }

        newDependencyIdentifiers.add(tempIdentifierLiteral.getName());
        return newDependencyIdentifiers;
    }

}
