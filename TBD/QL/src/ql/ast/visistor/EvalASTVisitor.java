package ql.ast.visistor;

import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;

/**
 * Created by Erik on 14-2-2017.
 */
public class EvalASTVisitor extends ASTVisitor<Value> {

    @Override
    public Value visit(QLIdent node) {
        //TODO fix this
        return new UndefinedValue();
    }

    @Override
    public Value visit(QLBoolean node) {
        return node.toValue();
    }

    @Override
    public Value visit(QLInt node) {
        return node.toValue();
    }

    @Override
    public Value visit(QLString node) {
        return node.toValue();
    }

    @Override
    public Value visit(QLFloat node) {
        return node.toValue();
    }

    @Override
    public Value visit(Add node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.add(right);
    }

    @Override
    public Value visit(Div node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.div(right);
    }

    @Override
    public Value visit(Eq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.eq(right);
    }

    @Override
    public Value visit(GEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gEq(right);
    }

    @Override
    public Value visit(GT node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gT(right);
    }

    @Override
    public Value visit(LEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lEq(right);
    }

    @Override
    public Value visit(LT node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lT(right);
    }

    @Override
    public Value visit(Mul node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.mul(right);
    }

    @Override
    public Value visit(NEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.nEq(right);
    }

    @Override
    public Value visit(Sub node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.sub(right);
    }

    @Override
    public Value visit(And node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.and(right);
    }

    @Override
    public Value visit(Or node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.or(right);
    }

    @Override
    public Value visit(Pos node) {
        Value expr = node.getExpr().accept(this);
        return expr.pos();
    }

    @Override
    public Value visit(Not node) {
        Value expr = node.getExpr().accept(this);
        return expr.not();
    }

    @Override
    public Value visit(Neg node) {
        Value expr = node.getExpr().accept(this);
        return expr.neg();
    }

}
