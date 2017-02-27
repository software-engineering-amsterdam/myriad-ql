package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 */
public abstract class Type extends ASTNode {

    //TODO type should not be a string!
    final String type;

    public Type(String type)
    {
        this.type = type;
    }

}
