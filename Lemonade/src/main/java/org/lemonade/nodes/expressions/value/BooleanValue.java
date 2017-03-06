package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.Position;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BooleanValue extends Value<Boolean> {

    public BooleanValue(String value) {
        super(new QLBooleanType(), Boolean.parseBoolean(value));
    }

    public BooleanValue(boolean value) {
        super(new QLBooleanType(), value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.getValue());
    }

    public BooleanValue and(BooleanValue that) {
        return new BooleanValue(this.getValue() && that.getValue());
    }

    public BooleanValue or(BooleanValue that) {
        return new BooleanValue(this.getValue() || that.getValue());
    }

    public BooleanValue not(){
        return new BooleanValue(!this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanValue)){
            return false;
        }
        BooleanValue that = (BooleanValue) obj;
        return this.getValue() == that.getValue();
    }

    public Expression bang() {
        return new BooleanValue(!this.getValue());
    }

}
