package org.lemonade.nodes.expressions;

import org.lemonade.nodes.ASTNode;
import org.lemonade.visitors.ExpressionVisitor;

public abstract class Expression extends ASTNode {

    public Expression() {

    }

    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
}
