package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class StringLit extends Literal<String> implements Comparable<StringLit>{

    public StringLit(QLType type, String value) {
        super(type, value);
        assert type instanceof QLStringType;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringLit)){
            return false;
        }
        StringLit that = (StringLit) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(StringLit that) {
        return this.getValue().compareTo(that.getValue());
    }
}
