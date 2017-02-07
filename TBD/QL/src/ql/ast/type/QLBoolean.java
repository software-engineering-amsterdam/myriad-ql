package ql.ast.type;

import ql.ast.ASTNode;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLBoolean implements ASTNode {
    private boolean qlBoolean;

    public QLBoolean(boolean qlBoolean) {
        this.qlBoolean = qlBoolean;
    }
}
