package org.qls.ast.widget;

import org.ql.ast.type.*;

public class SpinboxWidget extends Widget {
    @Override
    public void initializeSupportedTypes() {
        getSupportedTypes().add(new FloatType());
        getSupportedTypes().add(new IntegerType());
        getSupportedTypes().add(new MoneyType());
    }
}
