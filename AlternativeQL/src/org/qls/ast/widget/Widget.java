package org.qls.ast.widget;

import org.ql.ast.Node;
import org.ql.ast.type.*;

public abstract class Widget extends Node implements TypeCompatibility {
    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        throw new AssertionError();
    }

    public boolean isCompatibleWith(BooleanType type) {
        return false;
    }

    public boolean isCompatibleWith(DateType type) {
        return false;
    }

    public boolean isCompatibleWith(FloatType type) {
        return false;
    }

    public boolean isCompatibleWith(IntegerType type) {
        return false;
    }

    public boolean isCompatibleWith(MoneyType type) {
        return false;
    }

    public boolean isCompatibleWith(StringType type) {
        return false;
    }

    public boolean isCompatibleWith(UnknownType type) {
        return false;
    }
}
