package org.lemonade.nodes.types;

/**
 *
 */
public class QLDecimalType extends QLNumberType {

    public QLDecimalType() {
        super(2);
    }

    @Override
    public String toString() {
        return "decimal";
    }

}
