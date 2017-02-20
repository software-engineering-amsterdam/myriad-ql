package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLFloat extends QLLiteral {
    private float qlFloat;

    public QLFloat(float qlFloat) {
        this.qlFloat = qlFloat;
    }

    public float getQlFloat() {
        return qlFloat;
    }

    public float getValue() {
        return qlFloat;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
