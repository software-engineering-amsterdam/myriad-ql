package org.uva.taxfree.model;

import java.util.ArrayList;

public abstract class Node {

    private Node mParent;
    private ArrayList<Node> mChildren;

    public Node() {
        mChildren = new ArrayList<>();
    }

    public boolean addChild(Node child) {
        if (mChildren.contains(child)) {
            return false;
            //throw new ASTException("duplicate registration of child");
        }
        mChildren.add(child);
        return true;
    }

    public abstract String getId();

    public void retrieveQuestions(ArrayList<NamedNode> list) {
        for (Node child : mChildren) {
            child.addQuestion(list);
        }
        addQuestion(list);
    }

    protected void addQuestion(ArrayList<NamedNode> list) {
        // Intentionally left blank
    }

    public boolean isVisible(){
        return false;
    }

    public abstract String getType();
}