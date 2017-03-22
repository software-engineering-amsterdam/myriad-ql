package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.visitors.ExpressionVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Base Expression class. Where Questions, etc. are Statements, things that return results
 * when evaluated are Expressions.
 */
public abstract class Expression extends TreeNode {

    @Override
    public int getLine() {
        return super.getLine();
    }

    @Override
    public int getColumn() {
        return super.getColumn();
    }

    public abstract <T, C> T accept(ExpressionVisitor<T, C> visitor, C context);
}
