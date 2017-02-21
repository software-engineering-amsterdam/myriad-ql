package org.lemonade.nodes.types;

/**
 *
 */
public abstract class QLNumberType extends QLType implements QLComparableType {
    public QLNumberType() {
    }

    @Override
    public boolean isOf(QLType other) {
        return other instanceof QLNumberType;
    }
}
