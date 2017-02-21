package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Number implements ASTNode {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    public Number(String value) {
        setValue(value);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setValue(String value) {
        try {
            this.value = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            this.value = 0;
        }
    }

    public double doubleValue() {
        return value;
    }

    public int intValue() {
        return (int) value;
    }

    public boolean isInteger() {
        return value % 1 == 0;
    }

    public Number add(Number toAdd) {
        this.value += toAdd.doubleValue();
        return this;
    }

    public Number subtract(Number toSubtract) {
        this.value -= toSubtract.doubleValue();
        return this;
    }

    public Number divide(Number toDivide) {
        this.value /= toDivide.doubleValue();
        return this;
    }

    public Number multiply(Number toMultiply) {
        this.value *= toMultiply.doubleValue();
        return this;
    }

    @Override
    public String toString() {
        if (this.isInteger()) {
            return "" + (int) this.value;
        } else {
            return "" + this.value;
        }
    }
}


