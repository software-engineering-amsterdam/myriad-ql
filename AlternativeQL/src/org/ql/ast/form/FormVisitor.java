package org.ql.ast.form;

import org.ql.ast.Form;

public interface FormVisitor<T> {
    T visit(Form form);
}
