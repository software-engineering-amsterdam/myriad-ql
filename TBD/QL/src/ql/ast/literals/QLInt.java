package ql.ast.literals;

import ql.ast.values.IntValue;
import ql.visistor.interfaces.ExpressionVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLInt extends QLLiteral {
    private final int qlInteger;

    public QLInt(int qlInteger, int rowNumber) {
        super(rowNumber);
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

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
