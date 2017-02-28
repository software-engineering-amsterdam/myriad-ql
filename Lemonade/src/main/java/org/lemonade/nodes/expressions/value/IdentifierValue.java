package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IdentifierValue extends Value<String> implements Comparable<IdentifierValue>{

    public IdentifierValue(QLType type, String value) {
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
        if (!(obj instanceof IdentifierValue)){
            return false;
        }
        IdentifierValue that = (IdentifierValue) obj;
        return this.getValue() == that.getValue();
    }

    public int compareTo(IdentifierValue that) {
        return this.getValue().compareTo(that.getValue());
    }
}
