package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BooleanLit extends Literal {
    private boolean value;

    public BooleanLit(QLType type, String value) {
        super(type);
        assert type instanceof QLBooleanType;
        this.value = Boolean.parseBoolean(value);
    }


    public BooleanLit(QLType type, boolean value) {
        super(type);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    public boolean getValue() {
        return value;
    }

    public BooleanLit and(BooleanLit that) {
        return new BooleanLit(new QLBooleanType(), this.value && that.value);
    }

    public BooleanLit or(BooleanLit that) {
        return new BooleanLit(new QLBooleanType(), this.value || that.value);
    }

    public BooleanLit neg(){
        return new BooleanLit(new QLBooleanType(), !this.value);
    }
}
