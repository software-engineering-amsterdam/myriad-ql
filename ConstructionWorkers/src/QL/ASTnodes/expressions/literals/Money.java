/**
 * Money.java.
 */

package QL.ASTnodes.expressions.literals;

import java.math.BigDecimal;
import QL.ASTnodes.LineNumber;
import QL.ASTnodes.types.MoneyType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.visitors.ExpressionVisitor;

public class Money extends Literal {

    private final Type type;
    private final BigDecimal value;

    public Money(BigDecimal value, LineNumber location) {
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
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
