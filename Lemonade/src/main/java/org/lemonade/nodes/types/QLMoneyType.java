package org.lemonade.nodes.types;

/**
 *
 */
public class QLMoneyType extends QLNumberType {

    public QLMoneyType() {
        super(3);
    }

    @Override
    public String toString() {
        return "money";
    }
}
