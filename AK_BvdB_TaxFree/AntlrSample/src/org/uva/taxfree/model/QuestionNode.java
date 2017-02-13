package org.uva.taxfree.model;

import org.uva.taxfree.util.LogWindow;

import javax.swing.*;

public class QuestionNode extends Node {

    private String mDescription;
    private Value mValue;

    public QuestionNode(String name, String type, Node parent) {
        super(name, parent);
        mValue = createValue(type);


        //addChild(new DescriptionNode());
    }

    public boolean isValid() {
        return (null != mValue);
    }

    public JComponent getWidget() {
        return mValue.getWidget(mDescription);
    }

    // look at dynamic dispatch
    private Value createValue(String type) {
        switch (type) {
            case "string": {
                return new StringValue();
            }
            case "boolean": {
                return new BooleanValue();
            }
            default: {
                LogWindow.error(type + " does not represent a valid type!");
            }
        }
        return null;
    }


}
