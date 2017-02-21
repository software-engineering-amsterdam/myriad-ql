package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public abstract class ASTNode {
    public abstract <T> T accept(ASTVisitor<T> visitor);

    private int rowNumber;
    public ASTNode (int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
