/**
 * TypeChecker.java.
 */

package semanticChecker;

import ASTnodes.Form;
import ASTnodes.expressions.binaries.equality.*;
import ASTnodes.expressions.binaries.logic.*;
import ASTnodes.expressions.binaries.numerical.*;
import ASTnodes.expressions.literals.*;
import ASTnodes.expressions.unaries.*;
import ASTnodes.statements.*;
import ASTnodes.types.*;
import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.visitors.FormAndStatementVisitor;
import semanticChecker.messageHandling.MessageData;
import semanticChecker.messageHandling.errors.CyclicDependencyError;
import semanticChecker.messageHandling.errors.InvalidTypeError;
import semanticChecker.messageHandling.errors.UndefinedQuestionError;

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
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(OR expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(Addition expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(Division expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(Multiplication expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(Subtraction expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(Negation expression) {
        Type type = expression.getExpression().accept(this);
        return expression.checkType(type);
    }

    @Override
    public Type visit(Negative expression) {
        Type type = expression.getExpression().accept(this);
        return expression.checkType(type);
    }

    @Override
    public Type visit(Parenthesis expression) {
        Type type = expression.getExpression().accept(this);
        return expression.checkType(type);
    }

    @Override
    public Type visit(Positive expression) {
        Type type = expression.getExpression().accept(this);
        return expression.checkType(type);
    }

    @Override
    public Type visit(EQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(GT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(GTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(LT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(LTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
    }

    @Override
    public Type visit(NEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.checkType(left, right);
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

        Type statementType = statement.checkType(expressionType);
        if (statementType.getClass().equals(UndefinedType.class)) {
            messages.addError(new InvalidTypeError(statement.getLocation(), new BooleanType()));
        }

        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
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

        if(expressionType == null) {
            messages.addError(new InvalidTypeError(statement.getLocation(), statement.getType()));
        } else {
            if (!expressionType.getClass().equals(statement.getType().getClass())) {
                messages.addError(new InvalidTypeError(statement.getLocation(), statement.getType()));
            }
        }
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        if (identifierToTypeMap.get(identifier.getName()) == null) {
            messages.addError(new UndefinedQuestionError(identifier.getLocation(), identifier));
        }

        if (tempIdentifierLiteral != null) {
            if (tempIdentifierLiteral.getName().equals(identifier.getName())) {
                messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLocation(),
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
            messages.addError(new CyclicDependencyError(tempIdentifierLiteral.getLocation(), tempIdentifierLiteral, start));
        }
        updateDependencyData(start.getName());
    }

    private boolean checkRevertedDependency(String end) {
        List<String> toDependencies = dependencyList.getDependencyNames(end);

        if (toDependencies.contains(tempIdentifierLiteral.getName())) {
            return true;
        }
        return false;
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
}
