package org.ql.ast;
import org.ql.ast.expression.Visitor;

public abstract class Expression extends AbstractNode {
    public abstract <T> T accept(Visitor<T> visitor) throws Throwable;
}
