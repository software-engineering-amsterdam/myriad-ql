package org.lemonade;

/**
 *
 */
public abstract class Block extends ASTNode{
    public Block(int lineNo, ASTNode parent) {
        super(lineNo, parent);
    }
}
