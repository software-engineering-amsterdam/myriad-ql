package org.lemonade.nodes.types;

/**
 *
 */
public class QLDateType extends QLType {
    public QLDateType() {
    }

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
