package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

import java.math.BigDecimal;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Number extends Value implements ASTNode {
    protected BigDecimal value;

    public Number(double value) {
        setValue(value);
    }

    public Number(String value) {
        setValue(value);
    }

    public void setValue(double value) {
        this.value = new BigDecimal(value);
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = new BigDecimal(value);
        } catch (NumberFormatException ex) {
            this.value = new BigDecimal(0);
        }
    }

    @Override
    public Type getType() {
        return Type.DEC;
    }

    @Override
    public boolean conformsToType(Type type) {
        return type == Type.DEC || type == Type.INT;
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    public int intValue() {
        return value.intValue();
    }

    public boolean isInteger() {
        return value.doubleValue() % 1 == 0;
    }

    public Number add(Number toAdd) {
        this.value = this.value.add(toAdd.value);
        return this;
    }

    public Number subtract(Number toSubtract) {
        this.value = this.value.subtract(toSubtract.value);
        return this;
    }

    public Number divide(Number toDivide) {
        this.value = this.value.divide(toDivide.value);
        return this;
    }

    public Number multiply(Number toMultiply) {
        this.value = this.value.multiply(toMultiply.value);
        return this;
    }

    @Override
    public String computableString() {
        return toString();
    }

    @Override
    public String toString() {
        if (this.isInteger()) {
            return "" + this.value.toBigInteger();
        } else {
            return "" + this.value;
        }
    }
}


