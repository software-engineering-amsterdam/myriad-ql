package ql.ast.literals;

import ql.ast.values.IntValue;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLInt implements QLLiteral {
    private final int qlInteger;

    public QLInt(int qlInteger) {
        this.qlInteger = qlInteger;
    }

    public int getValue() {
        return qlInteger;
    }

    @Override
    public String toString() {
        return String.valueOf(qlInteger);
    }

    public IntValue toValue() {
        return new IntValue(qlInteger);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
