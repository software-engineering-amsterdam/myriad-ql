package org.lemonade.nodes;

import org.lemonade.visitors.interfaces.BaseVisitor;

public abstract class Body extends ASTNode {

    public abstract boolean isQuestion();

    public abstract boolean isConditional();

    public abstract <T> T accept(BaseVisitor<T> visitor);

}
