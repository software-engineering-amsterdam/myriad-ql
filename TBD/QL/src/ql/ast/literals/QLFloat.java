package ql.ast.literals;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLFloat extends QLLiteral {
    private final float qlFloat;

    public QLFloat(float qlFloat) {
        this.qlFloat = qlFloat;
    }

    public float getValue() {
        return qlFloat;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
