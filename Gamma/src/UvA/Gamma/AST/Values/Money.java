package UvA.Gamma.AST.Values;

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
