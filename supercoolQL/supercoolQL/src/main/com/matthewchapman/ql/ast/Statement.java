package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.visitor.QLStatementVisitor;

/**
 * Created by matt on 21/02/2017.
 * <p>
 * Base Statement class. All statements derive from this. Implements visitable
 * to allow derived classes to be visited.
 */
public abstract class Statement extends TreeNode {

    @Override
    public int getLine() {
        return super.getLine();
    }

    @Override
    public int getColumn() {
        return super.getColumn();
    }

    public abstract <T, C> T accept(QLStatementVisitor<T, C> visitor, C context);
}
