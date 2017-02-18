package org.lemonade.expression;

import org.lemonade.ASTNode;
import org.lemonade.QLType;

/**
 *
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
