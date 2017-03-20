package ql.gui.expressions.monop;

import ql.gui.GUIEvaluator;
import ql.gui.GUIExpr;
import ql.gui.expressions.GUIMonOp;

/**
 * Created by Erik on 7-2-2017.
 */
public class GUINot extends GUIMonOp {
    public GUINot(GUIExpr expr) {
        super(expr);
    }

    @Override
    public <T> T accept(GUIEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
