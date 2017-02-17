package checkSemantics;

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
import checkSemantics.messageHandling.MessageData;
import checkSemantics.messageHandling.errors.InvalidType;

import java.util.HashMap;

/**
 * Created by LGGX on 15-Feb-17.
 */
public class TypeChecker implements FormAndStatementVisitor<Void>, ExpressionVisitor<Type> {

    private HashMap<String, Type> identifierMap;
    private MessageData messageLists;

    public TypeChecker(Form ast, HashMap identifierMap, MessageData messageLists) {

        this.messageLists = messageLists;
        this.identifierMap = identifierMap;
        ast.accept(this);

    }

    @Override
    public Type visit(AND expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(Addition expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(Subtraction expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(OR expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(Negation expression) {
        Type type = expression.getExpression().accept(this);
        return expression.getType(type);
    }

    @Override
    public Type visit(Negative expression) {
        Type type = expression.getExpression().accept(this);
        return expression.getType(type);
    }

    @Override
    public Type visit(Positive expression) {
        Type type = expression.getExpression().accept(this);
        return expression.getType(type);
    }

    @Override
    public Type visit(Parenthesis expression) {
        Type type = expression.getExpression().accept(this);
        return expression.getType(type);
    }

    @Override
    public Type visit(EQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(NEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(Division expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(Multiplication expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(GT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(GTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(LT expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
    }

    @Override
    public Type visit(LTEQ expression) {
        Type left = expression.getLeft().accept(this);
        Type right = expression.getRight().accept(this);
        return expression.getType(left, right);
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
        Type type = statement.getExpression().accept(this);

        if (!type.getClass().equals(statement.getType().getClass())) {
            messageLists.addError(new InvalidType(statement.getLocation(), statement.getType()));
            //System.out.println("Incompatible types for computed question!");
        }

        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        Type expression = statement.getExpression().accept(this);

        Type type = statement.getType(expression);

        if (type.getClass().equals(new UndefinedType().getClass())) {
            messageLists.addError(new InvalidType(statement.getLocation(), new BooleanType()));
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
        String context = identifier.getName();

        Type type = identifierMap.get(identifier.getName());

        if (context == null) {
            System.out.println("Undefined identifier name!");
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


    public HashMap getMap() {
        return this.identifierMap;
    }
}
