package org.uva.taxfree.model;

import javax.swing.*;
import java.util.ArrayList;

public abstract class NamedNode extends Node {

    private String mDescription;
    private String mId;

    public NamedNode(String description, String id) {
        mDescription = description;
        mId = id;
    }

    public JPanel getWidget() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.add(new JLabel(mDescription));
        fillPanel(widgetPanel);
        return widgetPanel;
    }

    protected abstract void fillPanel(JPanel parent);

    public String getId() {
        return mId;
    }

    protected void addQuestion(ArrayList<NamedNode> list) {
        list.add(this);
    }

}
