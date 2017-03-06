package UvA.Gamma.AST.Values;

import UvA.Gamma.Validation.TypeChecker;

import java.math.RoundingMode;

/**
 * Created by Tjarco, 21-02-17.
 */
public class Money extends Number {

    public Money(double value) {
        super(value);
    }

    public Money(String value) {
        super(value);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value.replaceAll("€|(\\.-)", ""));
    }

    @Override
    public Type getType() {
        return Type.MONEY;
    }

    @Override
    public boolean validate(String value, TypeChecker typeChecker) {
        return typeChecker.checkMoney(value);
    }

    @Override
    public boolean conformsToType(Type type) {
        return super.conformsToType(type) || type == Type.MONEY;
    }

    @Override
    public String computableString() {
        return super.toString();
    }

    @Override
    public String toString() {
        if (this.isInteger())
            return "€" + value.intValue() + ",-";
        else
            return "€" + value.setScale(2, RoundingMode.CEILING);
    }
}
