package org.lemonade.visitors;

import org.lemonade.nodes.Form;

/**
 *
 */
public interface FormVisitor<T> {
    T visit(Form form);
}
