package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLBoolean implements ASTNode, QLLiteral {
    private boolean qlBoolean;

    public QLBoolean(boolean qlBoolean) {
        this.qlBoolean = qlBoolean;
    }
}
