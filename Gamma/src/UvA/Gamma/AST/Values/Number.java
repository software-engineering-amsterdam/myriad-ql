package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.TypeChecker;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Number extends Value {
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
        return Type.DECIMAL;
    }

    @Override
    public boolean conformsToType(Type type) {
        return type == Type.DECIMAL || type == Type.INTEGER || type == Type.BOOLEAN;
    }

    public BigInteger intValue() {
        return value.toBigInteger();
    }

    public boolean isInteger() {
        return value.doubleValue() % 1 == 0;
    }

    @Override
    public boolean validate(String value, TypeChecker typeChecker) {
        return this.isInteger() ? typeChecker.checkInteger(value) : typeChecker.checkDouble(value);
    }

    @Override
    public String computableString() {
        return toString();
    }

    @Override
    public void showQuestion(FXMLController screen, Question question) {
        screen.showQuestion(question);
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


