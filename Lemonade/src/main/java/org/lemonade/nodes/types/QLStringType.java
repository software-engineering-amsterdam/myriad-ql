package org.lemonade.nodes.types;

/**
 *
 */
public class QLStringType extends QLType {
    public QLStringType() {
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
