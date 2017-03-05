package qls.astnodes.styles;

import ql.astnodes.LineNumber;
import qls.astnodes.visitors.StyleSheetVisitor;

/**
 * Created by LGGX on 03-Mar-17.
 */
public class Color extends StyleType{

    public static final String NAME = "color";

    public Color(int value, LineNumber lineNumber) {
        super(NAME, Integer.toString(value), lineNumber);
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
