package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.Expr;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Sub implements ASTNode, Expr {
    private Expr left, right;

    public Sub(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public <T> T visitThis(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
