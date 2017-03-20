package org.qls.ast.widget;

import org.ql.ast.type.*;

public class TextWidget extends Widget {
    public boolean isCompatibleWith(FloatType type) {
        return true;
    }

    public boolean isCompatibleWith(IntegerType type) {
        return true;
    }

    public boolean isCompatibleWith(MoneyType type) {
        return true;
    }

    public boolean isCompatibleWith(StringType type) {
        return true;
    }
}
