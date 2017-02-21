package ql.ast.expressions.binop;

import ql.ast.Expr;
import ql.ast.expressions.ArithmeticOp;
import ql.ast.expressions.BinOp;
import ql.ast.expressions.RelationalOp;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Mul extends BinOp implements ArithmeticOp {
    public Mul(Expr left, Expr right) {
        super(left, right);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
