package org.ql.ast.form;

import org.ql.ast.Form;

public interface FormVisitor<T, C> {
    T visitForm(Form form, C context);
}
