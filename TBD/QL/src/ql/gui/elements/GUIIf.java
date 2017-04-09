package ql.gui.elements;

import ql.ast.Expr;
import ql.gui.evaluator.BaseEvaluator;
import ql.values.BooleanValue;
import ql.values.UndefinedValue;
import ql.values.Value;


/**
 * Created by Erik on 28-2-2017.
 */
public class GUIIf extends GUIElement {
    private final GUIElement ifStatements;
    private final Expr condition;
    private Boolean currentValue = null;

    public GUIIf(Expr condition, GUIElement ifStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;
    }

    public void update (Value value) {
        if (value instanceof UndefinedValue) {
            if (currentValue == null) {
                return;
            }
            this.getChildren().remove(ifStatements);
        }
        boolean aBoolean = ((BooleanValue) value).getValue();
        if (aBoolean) {
            this.getChildren().add(ifStatements);
        }else {
            this.getChildren().remove(ifStatements);
        }
        currentValue = aBoolean;
    }
    public GUIElement getIfStatements() {
        return ifStatements;
    }

    public Expr getCondition() {
        return condition;
    }

    public Boolean getConditionValue() {
       return currentValue;
    }

    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
