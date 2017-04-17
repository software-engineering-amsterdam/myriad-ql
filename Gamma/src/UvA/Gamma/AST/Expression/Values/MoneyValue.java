package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.MoneyType;
import UvA.Gamma.AST.Types.Type;

import java.math.BigDecimal;

/**
 * Created by Tjarco, 17-04-17.
 */
public class MoneyValue extends NumberValue {
    public MoneyValue(String value) {
        super(value);
    }

    public MoneyValue(BigDecimal value) {
        super(value);
    }

    @Override
    public Type getType() {
        return new MoneyType();
    }

    @Override
    public boolean conformsToType(Type type) {
        return super.conformsToType(type) || type.equalsType(this.getType());
    }

    @Override
    protected NumberValue createNew(BigDecimal value) {
        return new MoneyValue(value);
    }

    @Override
    public String toString() {
        if (this.isInteger()) {
            return "€" + this.value + ",-";
        } else {
            return "€" + value.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }
}
