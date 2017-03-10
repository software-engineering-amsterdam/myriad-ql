package qls.astnodes.styles;

import ql.astnodes.LineNumber;

/**
 * Created by LGGX on 03-Mar-17.
 */
public class UndefinedStyle extends Style {

    public UndefinedStyle(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
