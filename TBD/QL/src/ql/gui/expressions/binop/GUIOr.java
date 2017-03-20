package ql.gui.expressions.binop;

import ql.gui.GUIEvaluator;
import ql.gui.GUIExpr;
import ql.gui.expressions.GUIBinOp;
import ql.gui.expressions.LogicalOp;

/**
 * Created by Erik on 7-2-2017.
 */
public class GUIOr extends GUIBinOp {

    public GUIOr(GUIExpr left, GUIExpr right) {
        super(left, right);
    }

    @Override
    public <T> T accept(GUIEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
