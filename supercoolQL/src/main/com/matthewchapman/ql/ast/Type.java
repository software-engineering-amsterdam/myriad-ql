package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 */
public abstract class Type<T> extends TreeNode {

    //TODO type should not be a string!
    private final T value;

    public Type(T value) {
        this.value = value;
    }

}
