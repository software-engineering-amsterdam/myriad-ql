package org.uva.taxfree.model.node.declarations;

public class StringQuestion extends TextFieldQuestion {
    public StringQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new String();
    }
}
