package ql.gui.expressions;

import ql.gui.GUIExpr;

/**
 * Created by rico on 14-2-17.
 */
public abstract class GUIMonOp implements GUIExpr {
    private final GUIExpr expr;

    protected GUIMonOp(GUIExpr expr) {
        this.expr = expr;
    }

    public GUIExpr getExpr() {
        return expr;
    }
}
