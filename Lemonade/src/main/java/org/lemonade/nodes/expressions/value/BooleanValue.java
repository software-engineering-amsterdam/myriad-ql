package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BooleanValue extends Value<Boolean> {

    public BooleanValue(QLType type, String value) {
        super(type, Boolean.parseBoolean(value));
        assert type instanceof QLBooleanType;
    }

    public BooleanValue(QLType type, boolean value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.getValue());
    }

    public BooleanValue and(BooleanValue that) {
        return new BooleanValue(new QLBooleanType(), this.getValue() && that.getValue());
    }

    public BooleanValue or(BooleanValue that) {
        return new BooleanValue(new QLBooleanType(), this.getValue() || that.getValue());
    }

    public BooleanValue not(){
        return new BooleanValue(new QLBooleanType(), !this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanValue)){
            return false;
        }
        BooleanValue that = (BooleanValue) obj;
        return this.getValue() == that.getValue();
    }
}
