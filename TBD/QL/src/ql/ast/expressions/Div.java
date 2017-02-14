package ql.ast.expressions;

import ql.ast.Expr;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Div {
    private Expr left, right;

    public Div(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public <T> T visitThis(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
