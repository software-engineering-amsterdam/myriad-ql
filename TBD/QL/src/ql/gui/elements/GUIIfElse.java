package ql.gui.elements;

import ql.ast.Expr;
import ql.gui.evaluator.BaseEvaluator;
import ql.values.BooleanValue;
import ql.values.UndefinedValue;
import ql.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIIfElse extends GUIElement {
    private final GUIElement ifStatements;
    private final GUIElement elseStatements;
    private Boolean current = null;
    private final Expr condition;

    public GUIIfElse(Expr condition, GUIElement ifStatements, GUIElement elseStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;
        this.elseStatements = elseStatements;
    }

    public void update (Value value) {
        if (value instanceof UndefinedValue) {
            if (current == null) {
                return;
            }

            this.getChildren().remove(ifStatements);
            this.getChildren().remove(elseStatements);
        }


        boolean aBoolean = ((BooleanValue) value).getValue();

        if (current != null && current == aBoolean) {
            return;
        }

        if (aBoolean) {
            this.getChildren().add(ifStatements);
            this.getChildren().remove(elseStatements);

        }else {
            this.getChildren().add(elseStatements);
            this.getChildren().remove(ifStatements);
        }
        current = aBoolean;
    }

    public GUIElement getIfStatements() {
        return ifStatements;
    }

    public GUIElement getElseStatements() {
        return elseStatements;
    }

    public Expr getCondition() {
        return condition;
    }

    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
