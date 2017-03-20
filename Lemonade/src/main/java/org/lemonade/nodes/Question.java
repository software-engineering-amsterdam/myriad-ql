package org.lemonade.nodes;

import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.interfaces.BaseVisitor;

public class Question extends Body {
    private IdentifierLiteral identifier;
    private String label;
    private QLType type;

    public Question(IdentifierLiteral identifier, String label, QLType type) {
        super();
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public IdentifierLiteral getIdentifier() {
        return identifier;
    }

    public QLType getType() {
        return type;
    }

    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isQuestion() {
        return true;
    }

    @Override
    public boolean isConditional() {
        return false;
    }

}
