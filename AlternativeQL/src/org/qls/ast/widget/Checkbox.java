package org.qls.ast.widget;

import org.ql.ast.type.BooleanType;

public class Checkbox extends Widget {
    public boolean isCompatibleWith(BooleanType booleanType) {
        return true;
    }

    @Override
    public <T, C> T accept(WidgetVisitor<T, C> visitor, C context) {
        return visitor.visitCheckbox(this, context);
    }
}
