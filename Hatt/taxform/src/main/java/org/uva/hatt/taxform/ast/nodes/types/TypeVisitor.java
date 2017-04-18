package org.uva.hatt.taxform.ast.nodes.types;

public interface TypeVisitor<T> {
    T visit(Boolean node);

    T visit(Integer node);

    T visit(Money node);

    T visit(String node);

    T visit(Unknown unknown);
}
