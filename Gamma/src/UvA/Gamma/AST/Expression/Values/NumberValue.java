package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.DecimalType;
import UvA.Gamma.AST.Types.IntegerType;
import UvA.Gamma.AST.Types.MoneyType;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Tjarco, 21-03-17.
 */
public class NumberValue extends Value<NumberValue> {
    protected BigDecimal value;

    public NumberValue(String value) {
        this.setValue(value);
    }

    public NumberValue(BigDecimal value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value);
    }

    public NumberValue add(NumberValue other) {
        return createNew(this.value.add(other.value));
    }

    public NumberValue subtract(NumberValue other) {
        return createNew(this.value.subtract(other.value));
    }

    public NumberValue multiply(NumberValue other) {
        return createNew(this.value.multiply(other.value));
    }

    public NumberValue divide(NumberValue other) {
        return createNew(this.value.divide(other.value, RoundingMode.HALF_EVEN));
    }

    public BooleanValue smallerThan(NumberValue other) {
        return new BooleanValue(this.value.compareTo(other.value) < 0);
    }

    public boolean isInteger() {
        return value.signum() == 0 || value.scale() <= 0 || value.stripTrailingZeros().scale() <= 0;
    }

    protected NumberValue createNew(BigDecimal value) {
        return new NumberValue(value);
    }

    @Override
    public Type getType() {
        return isInteger() ? new IntegerType() : new DecimalType();
    }

    @Override
    public boolean conformsToType(Type type) {
        return type.equalsType(new IntegerType()) || type.equalsType(new DecimalType()) || type.equalsType(new MoneyType());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Value value() {
        return this;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public BooleanValue equals(NumberValue other) {
        return new BooleanValue(value.compareTo(other.value) == 0);
    }
}
