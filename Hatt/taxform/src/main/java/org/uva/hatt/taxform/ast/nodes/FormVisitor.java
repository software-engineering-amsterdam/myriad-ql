package org.uva.hatt.taxform.ast.nodes;

public interface FormVisitor<T> {
    T visit(Form node);
}
