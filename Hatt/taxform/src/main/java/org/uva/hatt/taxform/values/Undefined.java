package org.uva.hatt.taxform.values;

public class Undefined extends Value {

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
