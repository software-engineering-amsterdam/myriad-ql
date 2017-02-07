package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLFloat implements ASTNode, QLLiteral {
    private float qlFloat;

    public QLFloat(float qlFloat) {
        this.qlFloat = qlFloat;
    }
}
