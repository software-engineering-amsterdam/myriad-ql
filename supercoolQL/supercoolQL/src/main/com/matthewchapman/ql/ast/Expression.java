package com.matthewchapman.ql.ast;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Base Expression class. Where Questions, etc. are Statements, things that return results
 * when evaluated are Expressions.
 */
public abstract class Expression extends TreeNode implements QLVisitable {

    protected void setLine(int line) {
        super.setLine(line);
    }

    protected void setColumn(int column) {
        super.setColumn(column);
    }

    public int getLine(){ return super.getLine(); }

    public int getColumn(){ return super.getColumn(); }

}
