package org.ql.typechecker.expression;

import org.ql.ast.Node;

public abstract class TypeError extends Throwable {
    public abstract Node getNode();
}
