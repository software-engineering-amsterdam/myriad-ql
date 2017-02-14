/**
 * Money.java.
 */

package ASTnodes.expressions.literals;

import java.math.BigDecimal;
import ASTnodes.CodeLocation;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.visitors.AllVisitors;

public class Money extends Literal {

    private final Type type;
    private final BigDecimal value;

    public Money(BigDecimal value, CodeLocation location) {
        super(location);
        this.value = value;
        this.type = new MoneyType(location);
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
