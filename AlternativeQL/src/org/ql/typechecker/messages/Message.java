package org.ql.typechecker.messages;

import org.ql.ast.Node;

public interface Message {

    Node getNode();

    String getMessage();
}
