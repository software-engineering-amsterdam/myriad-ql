package qls.ast.literals;

import ql.ast.Expr;
import qls.ast.ASTNode;

/**
 * Created by rico on 7-3-17.
 */
public abstract class QLSLiteral extends ASTNode {
    public QLSLiteral(int rowNumber) {
        super(rowNumber);
    }
}
