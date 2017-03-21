package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Types.DecimalType;
import UvA.Gamma.AST.Types.IntegerType;
import UvA.Gamma.AST.Types.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Tjarco, 21-03-17.
 */
public class NumberValue extends Expression implements Value<NumberValue> {
    private BigDecimal value;

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
        return new NumberValue(this.value.add(other.value));
    }

    public NumberValue subtract(NumberValue other) {
        return new NumberValue(this.value.subtract(other.value));
    }

    public NumberValue multiply(NumberValue other) {
        return new NumberValue(this.value.multiply(other.value));
    }

    public NumberValue divide(NumberValue other) {
        return new NumberValue(this.value.divide(other.value, RoundingMode.HALF_EVEN));
    }

    public BooleanValue smallerThan(NumberValue other) {
        return new BooleanValue(this.value.compareTo(other.value) < 0);
    }

    public boolean isInteger() {
        return value.signum() == 0 || value.scale() <= 0 || value.stripTrailingZeros().scale() <= 0;
    }

    @Override
    public Type getType() {
        return isInteger() ? new IntegerType() : new DecimalType();
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
