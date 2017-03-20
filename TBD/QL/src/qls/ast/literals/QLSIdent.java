package qls.ast.literals;

import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;
/**
 * Created by rico on 7-3-17.
 */
public class QLSIdent extends QLSLiteral {
    private final String qlsIdent;

    public QLSIdent(String qlsIdent, int rowNumber) {
        super(rowNumber);
        this.qlsIdent = qlsIdent;
    }

    public String getValue() {
        return qlsIdent;
    }

    public Value toValue() {
        return new UndefinedValue();
    }

}