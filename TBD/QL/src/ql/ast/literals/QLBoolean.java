package ql.ast.literals;

import ql.ast.values.BooleanValue;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLBoolean implements QLLiteral {
    private final boolean qlBoolean;

    public QLBoolean(boolean qlBoolean) {
        this.qlBoolean = qlBoolean;
    }

    public boolean getValue() {
        return qlBoolean;
    }

    @Override
    public String toString() {
        return String.valueOf(qlBoolean);
    }

    public BooleanValue toValue() {
        return new BooleanValue(qlBoolean);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
