package org.uva.taxfree.ast;

import org.uva.taxfree.model.Node;

public class AST {

    private static AST mInstance;

    private Node mRootNode;

    public static AST getInstance() {
        return mInstance;
    }

    public AST(Node rootNode) {
        mRootNode = rootNode;
        mInstance = this;
    }

    public Node getRootNode() {
        return mRootNode;
    }
}

