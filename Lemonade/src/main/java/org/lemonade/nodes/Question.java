package org.lemonade.nodes;

import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

public class Question extends Body {
    private String identifier;
    private String label;
    private QLType type;

    public Question(String identifier, String label, QLType type) {
        super();
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getIdentifier() {
        return identifier;
    }

    public QLType getType() {
        return type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
