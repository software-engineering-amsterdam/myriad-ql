package org.ql.ast.form;

public interface FormVisitor<T, C> {
    T visitForm(Form form, C context);
}
