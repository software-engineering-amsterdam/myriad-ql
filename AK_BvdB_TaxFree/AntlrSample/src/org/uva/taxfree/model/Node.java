package org.uva.taxfree.model;

import java.util.ArrayList;

public abstract class Node {

    private Node mParent;
    private ArrayList<Node> mChildren;

    public Node(Node parent) {
        mChildren = new ArrayList<>();
        mParent = parent;
        registerToParent();
    }


    private void registerToParent() {
        if (mParent != null) {
            mParent.addChild(this);
        }
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
}
