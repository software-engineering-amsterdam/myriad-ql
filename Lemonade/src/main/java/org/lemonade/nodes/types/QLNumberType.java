package org.lemonade.nodes.types;

/**
 *
 */
public abstract class QLNumberType extends QLType {
    public QLNumberType() {
    }

    @Override
    public boolean isOf(QLType other) {
        return other instanceof QLNumberType;
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
