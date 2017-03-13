package qls.ast.literals;

import ql.ast.values.IntValue;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by rico on 7-3-17.
 */
public class QLSInt extends QLSLiteral {
    private final int qlsInteger;

    public QLSInt(int qlsInteger, int rowNumber) {
        super(rowNumber);
        this.qlsInteger = qlsInteger;
    }

    public int getValue() {
        return qlsInteger;
    }

    @Override
    public String toString() {
        return String.valueOf(qlsInteger);
    }

    public IntValue toValue() {
        return new IntValue(qlsInteger);
    }
}
