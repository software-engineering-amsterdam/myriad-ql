package org.qls.ast.widget;

import org.ql.ast.type.BooleanType;

public class CheckboxWidget extends Widget {
    @Override
    public void initializeSupportedTypes() {
        getSupportedTypes().add(new BooleanType());
    }
}
