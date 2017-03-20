package ql.gui.expressions;

import ql.gui.GUIExpr;

/**
 * Created by rico on 14-2-17.
 */
public abstract class GUIBinOp implements GUIExpr {
    private final GUIExpr left, right;

    protected GUIBinOp(GUIExpr left, GUIExpr right) {
        this.left = left;
        this.right = right;
    }

    public GUIExpr getLeft() {
        return left;
    }

    public GUIExpr getRight() {
        return right;
    }
}
