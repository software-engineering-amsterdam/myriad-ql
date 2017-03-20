package ql.gui.expressions.monop;

import ql.ast.Expr;
import ql.gui.expressions.ArithmeticOp;
import ql.gui.expressions.MonOp;
import ql.visistor.interfaces.ExpressionVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Pos extends MonOp implements ArithmeticOp {

    public Pos(Expr expr, int rowNumber) {
        super(expr, rowNumber);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
