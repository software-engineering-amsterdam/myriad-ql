/**
 * MoneyType.java.
 */

package ASTnodes.types;

import ASTnodes.LineNumber;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.MoneyValue;

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
