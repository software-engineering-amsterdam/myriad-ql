package org.lemonade.nodes.expressions;

import org.lemonade.nodes.ASTNode;
import org.lemonade.visitors.ASTVisitor;

public abstract class Expression implements ASTNode {

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
