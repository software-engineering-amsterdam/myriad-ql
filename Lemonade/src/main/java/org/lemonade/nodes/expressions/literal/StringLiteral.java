package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public class StringLiteral extends Literal<String> implements Comparable<StringLiteral>{

    public StringLiteral(String value) {
        super(new QLStringType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringLiteral)){
            return false;
        }
        StringLiteral that = (StringLiteral) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(StringLiteral that) {
        return this.getValue().compareTo(that.getValue());
    }
}
