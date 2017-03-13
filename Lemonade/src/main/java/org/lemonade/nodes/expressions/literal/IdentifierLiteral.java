package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class IdentifierLiteral extends Literal<String> implements Comparable<IdentifierLiteral>{

    public IdentifierLiteral(String value) {
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
        if (!(obj instanceof IdentifierLiteral)){
            return false;
        }
        IdentifierLiteral that = (IdentifierLiteral) obj;
        return this.getValue() == that.getValue();
    }

    public int compareTo(IdentifierLiteral that) {
        return this.getValue().compareTo(that.getValue());
    }

}
