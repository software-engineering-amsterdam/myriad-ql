package org.ql.ast;

public interface Node {
    void accept(Visitor visitor);
}
