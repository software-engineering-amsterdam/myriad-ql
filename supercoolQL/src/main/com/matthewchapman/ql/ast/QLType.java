package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 */
public abstract class QLType extends ASTNode {

    //TODO type should not be a string!
    final String type;

    public QLType(String type)
    {
        this.type = type;
    }

}
