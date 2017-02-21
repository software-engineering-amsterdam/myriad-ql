/**
 * TypeChecker.java.
 *
 * TODO: Finish/refactor!
 */

package lexicalChecker;

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
import lexicalChecker.dependency.DependencyData;
import lexicalChecker.messageHandling.MessageData;
import lexicalChecker.messageHandling.errors.CyclicDependencyError;
import lexicalChecker.messageHandling.errors.InvalidTypeError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeChecker implements FormAndStatementVisitor<Void>, ExpressionVisitor<Type> {

    private final DependencyData dependencyList;
    private Map<String, Type> identifierMap;
    private MessageData messageLists;
    private Identifier tempIdentifierLiteral;

    public TypeChecker(Form ast, Map identifierMap, MessageData messageLists) {

        this.messageLists = messageLists;
        this.identifierMap = identifierMap;
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
    public Type visit(Addition expression) {
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
    public Type visit(OR expression) {
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
    public Type visit(Positive expression) {
        Type type = expression.getExpression().accept(this);
        return expression.checkType(type);
    }

    @Override
    public Type visit(Parenthesis expression) {
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
    public Type visit(NEQ expression) {
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
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(SimpleQuestion statement) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion statement) {
        this.tempIdentifierLiteral = statement.getIdentifier();

        Type type = statement.getExpression().accept(this);

        this.tempIdentifierLiteral = null;

        if (!type.getClass().equals(statement.getType().getClass())) {
            messageLists.addError(new InvalidTypeError(statement.getLocation(), statement.getType()));
            //System.out.println("Incompatible types for computed question!");
        }

        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        Type expression = statement.getExpression().accept(this);

        Type type = statement.checkType(expression);

        if (type.getClass().equals(new UndefinedType().getClass())) {
            messageLists.addError(new InvalidTypeError(statement.getLocation(), new BooleanType()));
            //System.out.println("Incompatible types for IF statement expression!");
        }

        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }

        return null;
    }


    @Override
    public Type visit(MyBoolean literal) {
        return new BooleanType();
    }

    @Override
    public Type visit(Identifier identifier) {

        Type type = identifierMap.get(identifier.getName());

        if (this.tempIdentifierLiteral != null) {
            this.checkCyclicDependency(this.tempIdentifierLiteral, identifier);
        }

        return type;
    }

    @Override
    public Type visit(MyInteger literal) {
        return new IntegerType();
    }

    @Override
    public Type visit(MyString literal) {
        return new StringType();
    }

    @Override
    public Type visit(Money literal) {
        return new MoneyType();
    }

    private void checkCyclicDependency(Identifier end, Identifier start) {
        boolean revertedDependencyExists = this.checkRevertedDependency(start, end);
        if (revertedDependencyExists) {
            messageLists.addError(new CyclicDependencyError(end.getLocation(), end, start));
        }
        this.updateDependencyData(end, start);
    }

    private boolean checkRevertedDependency(Identifier end, Identifier start) {
        List<String> toDependencies =
                this.dependencyList.getIdDependencyNames(end);

        if ((toDependencies != null)
                && toDependencies.contains(start.getName()))
        {
            return true;
        }
        return false;
    }

    private void updateDependencyData(Identifier end, Identifier start) {

    }
}
