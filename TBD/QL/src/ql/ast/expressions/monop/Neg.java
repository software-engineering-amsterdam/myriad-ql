package ql.ast.expressions.monop;

import ql.ast.Expr;
import ql.ast.expressions.MonOp;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Neg extends MonOp {

    public Neg(Expr expr){
        super(expr);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
