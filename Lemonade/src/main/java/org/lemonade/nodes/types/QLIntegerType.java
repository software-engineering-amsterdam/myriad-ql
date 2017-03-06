package org.lemonade.nodes.types;

/**
 *
 */
public class QLIntegerType extends QLNumberType {

    public QLIntegerType() {
        super(1);
    }

    @Override
    public String toString() {
        return "integer";
    }
}
