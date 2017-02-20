package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class NEqBinary extends BinaryExpression {

    public NEqBinary() {
        super();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
