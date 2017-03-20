package org.qls.ast.widget;

import org.ql.ast.type.*;

public abstract class Widget extends TypeCompatibility {
    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        throw new AssertionError();
    }
}
