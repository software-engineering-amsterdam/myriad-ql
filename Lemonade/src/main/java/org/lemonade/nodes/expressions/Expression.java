package org.lemonade.nodes.expressions;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Position;
import org.lemonade.visitors.ASTVisitor;

public abstract class Expression extends ASTNode {

    public Expression() {
        super();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
