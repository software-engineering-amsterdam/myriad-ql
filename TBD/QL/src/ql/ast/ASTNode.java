package ql.ast;

/**
 * Created by Erik on 6-2-2017.
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
