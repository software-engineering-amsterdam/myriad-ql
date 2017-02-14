package org.uva.taxfree.model;

import javax.swing.*;

public abstract class QuestionNode extends Node {

    private String mDescription;
    private String mId;

    public QuestionNode(String description, String id, Node parent) {
        super(parent);
        mDescription = description;
        mId = id;
    }

    public JPanel getWidget() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.add(new JLabel(mId));
        fillPanel(widgetPanel);
        return widgetPanel;
    }

    protected abstract void fillPanel(JPanel parent);

    public String getId(){
        return mId;
    }
}
