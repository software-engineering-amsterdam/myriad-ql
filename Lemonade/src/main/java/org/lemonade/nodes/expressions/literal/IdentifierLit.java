package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IdentifierLit extends Literal {
    private String value;

    public IdentifierLit(QLType type, String value) {
        super(type);
        assert type instanceof QLStringType;//TODO can we do this?
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return value;
    }
}
