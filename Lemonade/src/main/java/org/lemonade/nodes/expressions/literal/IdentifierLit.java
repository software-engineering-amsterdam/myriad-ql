package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IdentifierLit extends Literal<String> implements Comparable<IdentifierLit>{

    public IdentifierLit(QLType type, String value) {
        super(type, value);
        assert type instanceof QLStringType;//TODO can we do this?
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
        if (!(obj instanceof IdentifierLit)){
            return false;
        }
        IdentifierLit that = (IdentifierLit) obj;
        return this.getValue() == that.getValue();
    }

    public int compareTo(IdentifierLit that) {
        return this.getValue().compareTo(that.getValue());
    }
}
