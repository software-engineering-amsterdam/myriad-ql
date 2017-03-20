package org.qls.ast.widget;

import org.ql.ast.type.*;

public class SpinboxWidget extends Widget {

    public boolean isCompatibleWith(FloatType type) {
        return true;
    }

    public boolean isCompatibleWith(IntegerType type) {
        return true;
    }

    public boolean isCompatibleWith(MoneyType type) {
        return true;
    }
}
