package ql.ast.visistor;

import ql.ast.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;

/**
 * Created by Erik on 14-2-2017.
 */
public class EvalASTVisitor implements ASTVisitor<QLLiteral> {
    @Override
    public QLLiteral visit(Form node) {
        return null;
    }

    @Override
    public QLLiteral visit(Statements node) {
        return null;
    }

    @Override
    public QLLiteral visit(If node) {
        return null;
    }

    @Override
    public QLLiteral visit(IfElse node) {
        return null;
    }

    @Override
    public QLLiteral visit(Question node) {
        return null;
    }

    @Override
    public QLLiteral visit(QLIdent node) {
        //TODO fix this
        return node;
    }

    @Override
    public QLLiteral visit(QLBoolean node) {
        return node;
    }

    @Override
    public QLLiteral visit(QLInt node) {
        return node;
    }

    @Override
    public QLLiteral visit(QLString node) {
        return node;
    }

    @Override
    public QLLiteral visit(QLFloat node) {
        return node;
    }

    @Override
    public QLLiteral visit(Add node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.add(right);
    }

    @Override
    public QLLiteral visit(Div node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.div(right);
    }

    @Override
    public QLLiteral visit(Eq node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.eq(right);
    }

    @Override
    public QLLiteral visit(GEq node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gEq(right);
    }

    @Override
    public QLLiteral visit(GT node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gT(right);
    }

    @Override
    public QLLiteral visit(LEq node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lEq(right);
    }

    @Override
    public QLLiteral visit(LT node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lT(right);
    }

    @Override
    public QLLiteral visit(Mul node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.mul(right);
    }

    @Override
    public QLLiteral visit(NEq node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.nEq(right);
    }

    @Override
    public QLLiteral visit(Sub node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.sub(right);
    }

    @Override
    public QLLiteral visit(And node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.and(right);
    }

    @Override
    public QLLiteral visit(Or node) {
        QLLiteral left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.or(right);
    }

    @Override
    public QLLiteral visit(Pos node) {
        QLLiteral expr = node.getExpr().accept(this);
        return expr.pos();
    }

    @Override
    public QLLiteral visit(Not node) {
        QLLiteral expr = node.getExpr().accept(this);
        return expr.not();
    }

    @Override
    public QLLiteral visit(Neg node) {
        QLLiteral expr = node.getExpr().accept(this);
        return expr.neg();
    }

}
