package org.lemonade.visitors;

import org.lemonade.nodes.Form;

/**
 *
 */
public class FormChecker implements FormVisitor<Boolean> {

    @Override
    public Boolean visit(Form form) {
        System.err.println("Form identifier: " + form.getIdentifier());
        return true;
    }
}
