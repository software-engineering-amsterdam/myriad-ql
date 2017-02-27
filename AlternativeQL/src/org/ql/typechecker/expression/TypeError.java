package org.ql.typechecker.expression;

import org.ql.ast.Node;

public interface TypeError {
    String getMessage();
    Node getNode();
}
