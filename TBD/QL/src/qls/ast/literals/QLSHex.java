package qls.ast.literals;

import ql.ast.values.StringValue;

/**
 * Created by rico on 7-3-17.
 */
public class QLSHex extends QLSLiteral {
    private final String qlsHex;

    public QLSHex(String qlsHex, int rowNumber) {
        super(rowNumber);
        this.qlsHex = qlsHex;
    }

    public String getValue() {
        return qlsHex;
    }


    public StringValue toValue() {
        return new StringValue(qlsHex);
    }

    @Override
    public String toString() {
        return qlsHex;
    }
}
