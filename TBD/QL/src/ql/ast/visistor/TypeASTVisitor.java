package ql.ast.visistor;

import ql.ast.*;
import ql.ast.environment.Env;
import ql.ast.environment.Scope;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.ast.types.*;
import ql.ast.visistor.interfaces.BaseVisitor;
import ql.ast.visistor.interfaces.ExpressionVisitor;
import ql.logger.Error;
import ql.logger.ErrorHandler;

import java.util.List;

/**
 * Created by Erik on 14-2-2017.
 */
public class TypeASTVisitor implements BaseVisitor<Void>, ExpressionVisitor<Type> {

    private ErrorHandler errorHandler;
    private final Env env;
    private Scope currentScope = null;

    public TypeASTVisitor(Env env) {
        this.env = env;
    }

    public void startVisitor(ErrorHandler errorHandler, Form node) {
        this.errorHandler = errorHandler;
        node.accept(this);
        errorHandler.showErrors();
    }

    @Override
    public Void visit(Form node) {
        node.getStatements().accept(this);
        return null;
    }

    public Void visit(Statements node) {
        currentScope = env.getScope(node);
        List<Statement> statements = node.getItems();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(If node) {
        Type expr = node.getCondition().accept(this);

        if (expr.equals(new ErrorType())) {
            errorHandler.addError(new Error("Error in the expression", node.getCondition().getRowNumber()));
        }

        if (!expr.equals(new BooleanType())) {
            errorHandler.addError(new Error("Condition Should be a boolean", node.getCondition().getRowNumber()));
        }

        node.getIfBlock().accept(this);
        return null;
    }

    @Override
    public Void visit(IfElse node) {
        Type expr = node.getCondition().accept(this);


        if (expr.equals(new ErrorType())) {
            errorHandler.addError(new Error("Error in the expression", node.getCondition().getRowNumber()));
        }

        if (!expr.equals(new BooleanType())) {
            errorHandler.addError(new Error("Condition Should be a boolean found " + expr.toString(),  node.getCondition().getRowNumber()));
        }

        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }

    @Override
    public Void visit(Question node) {
        return null;
    }

    @Override
    public Void visit(QuestionExpr node) {
        Type expr = node.getExpr().accept(this);
        if (!expr.equals(node.getType())) {
            errorHandler.addError(new Error("Wrong type for assignment expected " + node.getType() + " found " + expr, node.getRowNumber()));
        }

        if (expr.equals(new ErrorType())) {
            errorHandler.addError(new Error("Error in expression!", node.getRowNumber()));
        }


        return null;
    }

    @Override
    public Type visit(QLIdent node) {
        if (!env.contains(node.getValue())) {
            errorHandler.addError(new Error("Identifier " + node.getValue() + " doesn't exist!", node.getRowNumber()));
            return new ErrorType();
        }

        if (!currentScope.getScopes().contains(env.getQuestionScope(node.getValue()))) {
            errorHandler.addError(new Error("Identifier " + node.getValue() + " doesn't exist in this scope!", node.getRowNumber()));
            return new ErrorType();
        }

        return env.getQuestionType(node.getValue());
    }

    @Override
    public Type visit(QLBoolean node) {
        return new BooleanType();
    }

    @Override
    public Type visit(QLInt node) {
        return new IntType();
    }

    @Override
    public Type visit(QLString node) {
        return new StringType();
    }

    @Override
    public Type visit(QLFloat node) {
        return new FloatType();
    }

    @Override
    public Type visit(Add node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Div node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Eq node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(GEq node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(GT node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(LEq node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(LT node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Mul node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }


    @Override
    public Type visit(NEq node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Sub node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(And node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Or node) {
        Type left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.checkTypes(node, right);
    }

    @Override
    public Type visit(Pos node) {
        Type expr;
        expr = node.getExpr().accept(this);

        return expr.checkTypes(node);
    }

    @Override
    public Type visit(Neg node) {
        Type expr;
        expr = node.getExpr().accept(this);

        return expr.checkTypes(node);
    }

    @Override
    public Type visit(Not node) {
        Type expr;
        expr = node.getExpr().accept(this);

        return expr.checkTypes(node);
    }

}
