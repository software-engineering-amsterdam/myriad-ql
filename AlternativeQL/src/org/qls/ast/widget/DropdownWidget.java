package org.qls.ast.widget;

import org.ql.ast.type.*;

public class DropdownWidget extends Widget {
    private String yesText;
    private String noText;

    public DropdownWidget(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public void setYesText(String yesText) {
        this.yesText = yesText;
    }

    public String getNoText() {
        return noText;
    }

    public void setNoText(String noText) {
        this.noText = noText;
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
