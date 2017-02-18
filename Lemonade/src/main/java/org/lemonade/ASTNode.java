package org.lemonade;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ASTNode {
    private int lineNo;//Start in the code (for errors)
    private ASTNode parent;
    private List<ArrayList<ASTNode>> children;
    //Maybe I'm forgetting stuff

    public ASTNode (int lineNo, ASTNode parent) {
        this.lineNo = lineNo;
        this.parent = parent;
        this.children = new ArrayList<ArrayList<ASTNode>>();
    }

}

