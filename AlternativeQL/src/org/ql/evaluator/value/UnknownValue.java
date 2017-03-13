package org.ql.evaluator.value;

public class UnknownValue extends Value {
    @Override
    public Object getPlainValue() {
        return null;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;
    }
}
