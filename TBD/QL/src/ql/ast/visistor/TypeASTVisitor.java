package ql.ast.visistor;

import ql.ast.*;
import ql.ast.types.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.ast.environment.Environment;
import ql.logger.Error;
import ql.logger.ErrorHandler;

import java.util.List;

/**
 * Created by Erik on 14-2-2017.
 */
public class TypeASTVisitor extends ASTVisitor<Type>{

    private final ErrorHandler errorHandler = new ErrorHandler();
    private final Environment env;

    public TypeASTVisitor(Environment env) {
        this.env = env;
    }

    public Type startVisitor(ASTNode node) {
        node.accept(this);
        errorHandler.showErrors();
        return null;
    }

    public Type visit(Statements node) {
        env.setScope(node);
        List<Statement> statements = node.getItems();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(If node) {
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
    public Type visit(IfElse node) {
        Type expr = node.getCondition().accept(this);


        if (expr.equals(new ErrorType())) {
            errorHandler.addError(new Error("Error in the expression", node.getCondition().getRowNumber()));
        }

        if (!expr.equals(new BooleanType())) {
            errorHandler.addError(new Error("Condition Should be a boolean",  node.getCondition().getRowNumber()));
        }

        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }

    @Override
    public Type visit(QuestionExpr node) {
        Type expr = node.getExpr().accept(this);
        if (!expr.equals(node.getType())) {
            errorHandler.addError(new Error("Wrong type for assignment!", node.getRowNumber()));
        }

        if (expr.equals(new ErrorType())) {
            errorHandler.addError(new Error("Error in expression!", node.getRowNumber()));
        }


        return null;
    }

    @Override
    public Type visit(QLIdent node) {
        if (env.contains(node.getValue())) {
            return env.getVariableType(node.getValue());
        }
        errorHandler.addError(new Error("Identifier " + node.getValue() + " doesn't exist!", node.getRowNumber()));
        return new ErrorType();
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
