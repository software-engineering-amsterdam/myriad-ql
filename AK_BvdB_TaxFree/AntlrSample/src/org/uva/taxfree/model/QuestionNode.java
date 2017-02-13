package org.uva.taxfree.model;

/**
 * Created by Alex on 7-2-2017.
 */
public class QuestionNode extends Node {

    private String mDescription;
    private String mValue;

    public QuestionNode(String name, Node parent) {
        super(name, parent);


        //addChild(new DescriptionNode());
    }
}
