package org.ql.typechecker.exception;

import org.ql.ast.Node;

public interface TypeError {
    String getMessage();
    Node getNode();
}
