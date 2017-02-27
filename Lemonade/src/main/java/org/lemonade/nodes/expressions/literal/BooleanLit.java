package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BooleanLit extends Literal<Boolean> {

    public BooleanLit(QLType type, String value) {
        super(type, Boolean.parseBoolean(value));
        assert type instanceof QLBooleanType;
    }

    public BooleanLit(QLType type, boolean value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.getValue());
    }

    public BooleanLit and(BooleanLit that) {
        return new BooleanLit(new QLBooleanType(), this.getValue() && that.getValue());
    }

    public BooleanLit or(BooleanLit that) {
        return new BooleanLit(new QLBooleanType(), this.getValue() || that.getValue());
    }

    public BooleanLit not(){
        return new BooleanLit(new QLBooleanType(), !this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanLit)){
            return false;
        }
        BooleanLit that = (BooleanLit) obj;
        return this.getValue() == that.getValue();
    }
}
