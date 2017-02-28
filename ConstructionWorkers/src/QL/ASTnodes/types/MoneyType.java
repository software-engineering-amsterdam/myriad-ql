/**
 * MoneyType.java.
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.MoneyValue;

import java.math.BigDecimal;

public class MoneyType extends Type {

    public MoneyType() {
        super();
    }

    public MoneyType(LineNumber location) {
        super(location);
    }

    @Override
    public String toString() {
        return "Money";
    }

    @Override
    public MoneyValue getDefaultValue() {
        return new MoneyValue(new BigDecimal(0));
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
