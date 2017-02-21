package ql.ast.visistor;

import ql.ast.*;
import ql.ast.types.*;
import ql.ast.expressions.BinOp;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erik on 14-2-2017.
 */
public class TypeASTVisitor implements ASTVisitor<Type> {

    private final Map<String, Type> identTable = new HashMap<>();

    @Override
    public Type visit(Form node) {
        node.getStatements().accept(this);
        return null;
    }

    @Override
    public Type visit(Statements node) {
        List<Statement> statements = node.getStatements();
        for (Statement statement : statements) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Type visit(If node) {
        Type expr = node.getCondition().accept(this);

        if (!expr.equals(new BooleanType())) {
            throw new RuntimeException("Type error");
        }

        node.getIfBlock().accept(this);
        return null;
    }

    @Override
    public Type visit(IfElse node) {
        Type expr = node.getCondition().accept(this);

        if (!expr.equals(new BooleanType())) {
            throw new RuntimeException("Type error");
        }

        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
        return null;
    }

    @Override
    public Type visit(Question node) {
        if (identTable.containsKey(node.getId().getValue())) {
            throw new RuntimeException("Id already exist");
        }

        identTable.put(node.getId().getValue(), node.getType());

        if (node.hasExpr()) {
            Type expr = node.getExpr().accept(this);
            if (expr.equals(node.getType())) {
                return null;
            }
            throw new RuntimeException("Type error");
        }
        return null;
    }

    @Override
    public Type visit(QLIdent node) {
        if (identTable.containsKey(node.getValue())) {
            return identTable.get(node.getValue());
        }
        throw new RuntimeException("Unexpected variable");
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
        System.out.println(left + "          " + right);
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
