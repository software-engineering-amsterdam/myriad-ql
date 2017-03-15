package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 * Abstract Type, provides base class for all Type objects to derive from
 */
public abstract class Type extends TreeNode {

    public abstract boolean isCompatible(Type type);

    @Override
    public abstract String toString();

}
