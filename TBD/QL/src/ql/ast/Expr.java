package ql.ast;

import ql.ast.visistor.interfaces.ExpressionVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public abstract class Expr extends ASTNode {
    public Expr(int rowNumber) {
        super(rowNumber);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
