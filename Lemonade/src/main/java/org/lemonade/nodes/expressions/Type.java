package org.lemonade.nodes.expressions;

import org.lemonade.nodes.ASTNode;
import org.lemonade.QLType;
import org.lemonade.visitors.ExpressionVisitor;
import org.lemonade.visitors.TypeVisitor;

/**
 * A wrapper for QLType, couldn't use it in the Visitor otherwise..
 */
public class Type extends ASTNode {
    private QLType type;

    public Type(QLType type) {
        this.type = type;
    }

    public QLType getType() {
        return type;
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
