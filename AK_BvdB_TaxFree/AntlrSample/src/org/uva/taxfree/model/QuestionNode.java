package org.uva.taxfree.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.uva.taxfree.util.LogWindow;

public class QuestionNode extends Node {

    private String mDescription;
    private Value mValue;

    public QuestionNode(String name, String type, Node parent) {
        super(name, parent);
        mValue = createValue(type);


        //addChild(new DescriptionNode());
    }

    public boolean isValid(){
        return (null != mValue);
    }

    // look at dynamic dispatch
    private Value createValue(String type){
        switch(type)
        {
            case "string":
            {
                return new StringValue();
            }
            case "boolean":
            {
                return new BooleanValue();
            }
            default:
            {
                LogWindow.error(type + " does not represent a valid type!");
            }
        }
        return null;
    }
}
