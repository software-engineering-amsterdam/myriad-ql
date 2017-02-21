package org.uva.taxfree.model;

import javax.swing.*;

public class FormNode extends NamedNode {
    public FormNode(String description) {
        super(description, "form");
    }

    public String getType(){
        return "form";
    }

    @Override
    protected void fillPanel(JPanel parent) {
        ; // Intentionally left blank
    }
}
