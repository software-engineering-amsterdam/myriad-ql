package org.uva.taxfree.ast;

import org.uva.taxfree.model.Node;

public class AST {

    private Node mRootNode;

    public AST(Node rootNode) {
        mRootNode = rootNode;
    }

    public Node getRootNode() {
        return mRootNode;
    }
}

