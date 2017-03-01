package org.ql.typechecker.error;

import org.ql.ast.Node;

public abstract interface TypeError {
    String getMessage();
    Node getNode();
}
