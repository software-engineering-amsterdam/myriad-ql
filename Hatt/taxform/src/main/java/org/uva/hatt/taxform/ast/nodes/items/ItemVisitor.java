package org.uva.hatt.taxform.ast.nodes.items;

public interface ItemVisitor<T> {
    T visit(Question node);

    T visit(ComputedQuestion node);

    T visit(IfThen node);

    T visit(IfThenElse node);
}
