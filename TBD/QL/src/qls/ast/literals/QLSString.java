package qls.ast.literals;

import ql.ast.values.StringValue;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by rico on 7-3-17.
 */
public class QLSString extends QLSLiteral {
    private final String qlsString;

    public QLSString(String qlsString, int rowNumber) {
        super(rowNumber);
        this.qlsString = qlsString;
    }

    public String getValue() {
        return qlsString;
    }


    public StringValue toValue() {
        return new StringValue(qlsString);
    }

    @Override
    public String toString() {
        return qlsString;
    }
}
