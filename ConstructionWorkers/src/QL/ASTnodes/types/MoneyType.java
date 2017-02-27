/**
 * MoneyType.java.
 */

package QL.ASTnodes.types;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.visitors.TypeVisitor;
import QL.semanticChecker.formDataStorage.valueData.values.MoneyValue;

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
    public MoneyValue getDefaultState() {
        return new MoneyValue(new BigDecimal(0));
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
