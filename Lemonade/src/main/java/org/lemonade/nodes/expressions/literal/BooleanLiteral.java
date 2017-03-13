package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public class BooleanLiteral extends Literal<Boolean> {

    public BooleanLiteral(String value) {
        super(new QLBooleanType(), Boolean.parseBoolean(value));
    }

    public BooleanLiteral(boolean value) {
        super(new QLBooleanType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(this.getValue());
    }

    public BooleanLiteral and(BooleanLiteral that) {
        return new BooleanLiteral(this.getValue() && that.getValue());
    }

    public BooleanLiteral or(BooleanLiteral that) {
        return new BooleanLiteral(this.getValue() || that.getValue());
    }

    public BooleanLiteral not(){
        return new BooleanLiteral(!this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanLiteral)){
            return false;
        }
        BooleanLiteral that = (BooleanLiteral) obj;
        return this.getValue() == that.getValue();
    }

    public Expression bang() {
        return new BooleanLiteral(!this.getValue());
    }

}
