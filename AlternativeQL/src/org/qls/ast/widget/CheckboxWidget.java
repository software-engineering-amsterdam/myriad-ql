package org.qls.ast.widget;

import org.ql.ast.type.BooleanType;

public class CheckboxWidget extends Widget {
    public boolean isCompatibleWith(BooleanType booleanType) {
        return true;
    }
}
