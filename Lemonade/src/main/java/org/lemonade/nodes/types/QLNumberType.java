package org.lemonade.nodes.types;

/**
 *
 */
public abstract class QLNumberType extends QLType {
    public QLNumberType() {
    }

    @Override
    public boolean isOf(Class other) {
        return this.getClass().equals(other);
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
