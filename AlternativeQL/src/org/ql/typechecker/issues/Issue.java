package org.ql.typechecker.issues;

import org.ql.ast.Node;

public interface Issue {
    String getMessage();
    Node getNode();
}
