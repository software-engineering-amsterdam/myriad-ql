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

    public boolean isCompatibleWith(BooleanType booleanType) {
        return true;
    }

    public boolean isCompatibleWith(FloatType floatType) {
        return true;
    }

    public boolean isCompatibleWith(IntegerType integerType) {
        return true;
    }

    public boolean isCompatibleWith(MoneyType moneyType) {
        return true;
    }

    public boolean isCompatibleWith(StringType stringType) {
        return true;
    }
}
