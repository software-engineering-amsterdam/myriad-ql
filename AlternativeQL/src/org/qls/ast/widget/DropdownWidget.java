package org.qls.ast.widget;

import org.ql.ast.type.*;

public class DropdownWidget extends Widget {
    private final String yesText;
    private final String noText;

    public DropdownWidget(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public String getNoText() {
        return noText;
    }

    @Override
    public void initializeSupportedTypes() {
        getSupportedTypes().add(new BooleanType());
        getSupportedTypes().add(new FloatType());
        getSupportedTypes().add(new IntegerType());
        getSupportedTypes().add(new MoneyType());
        getSupportedTypes().add(new StringType());
    }
}
