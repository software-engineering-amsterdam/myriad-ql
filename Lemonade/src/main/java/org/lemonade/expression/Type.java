package org.lemonade.expression;

import org.lemonade.ASTNode;
import org.lemonade.QLType;

/**
 * A wrapper for QLType, couldn't use it in the Visitor otherwise..
 */
public class Type extends ASTNode{
    private QLType type;

    public Type(QLType type){
        this.type = type;
    }

    public QLType getType() {
        return type;
    }
}
