package ql.ast.type;

import ql.ast.ASTNode;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLFloat implements ASTNode {
    private float qlFloat;

    public QLFloat(float qlFloat) {
        this.qlFloat = qlFloat;
    }
}
