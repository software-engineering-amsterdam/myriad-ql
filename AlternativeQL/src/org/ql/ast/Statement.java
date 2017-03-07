package org.ql.ast;

import org.ql.ast.statement.StatementVisitor;

public abstract class Statement extends AbstractNode {
    public abstract <T> T accept(StatementVisitor<T> visitor);
}
