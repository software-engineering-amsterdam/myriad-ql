package org.uva.taxfree.model;

import javax.swing.*;

public class FormNode extends Node {
    private String mFormName;
    public FormNode(String label) {
        super();
        mFormName = label;
    }

    @Override
    public String toString() {
        return mFormName;
    }
}
