package ASTnodes.expressions.literals;

import java.math.BigDecimal;
import ASTnodes.CodeLocation;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 13-Feb-17.
 */
public class MONEY extends Literal {

    private final Type type;
    private final BigDecimal literal;

    public MONEY(BigDecimal literal, CodeLocation location) {
        super(location);
        this.literal = literal;
        this.type = new MoneyType(location);
    }

    @Override
    public BigDecimal getLiteral() {
        return literal;
    }

    public Type getType() {
        return type;
    }


    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return literal.toString();
    }

}
