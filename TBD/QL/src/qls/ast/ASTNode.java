package qls.ast;

import ql.visistor.ASTVisitor;

/**
 * Created by rico on 7-3-17.
 */
public abstract class ASTNode {

    private int rowNumber;
    public ASTNode (int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
