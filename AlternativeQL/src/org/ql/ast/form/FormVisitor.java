package org.ql.ast.form;

import org.ql.ast.Form;

public interface FormVisitor<T, C> {
    T visit(Form form, C context);
}
