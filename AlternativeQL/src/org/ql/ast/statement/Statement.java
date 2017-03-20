package org.ql.ast.statement;

import org.ql.ast.Node;
import org.ql.ast.statement.StatementVisitor;

public abstract class Statement extends Node {
    public abstract <T, C> T accept(StatementVisitor<T, C> visitor, C context);
}
