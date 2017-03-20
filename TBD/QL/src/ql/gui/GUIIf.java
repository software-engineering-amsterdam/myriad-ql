package ql.gui;

import javafx.scene.layout.VBox;
import ql.ast.Expr;
import ql.visistor.environment.Env;
import ql.values.BooleanValue;
import ql.values.UndefinedValue;
import ql.values.Value;


/**
 * Created by Erik on 28-2-2017.
 */
public class GUIIf extends GUIElement {
    private final GUIElement ifStatements;
    private final Expr condition;

    public GUIIf(Expr condition, GUIElement ifStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;

    }

    public void update (Value value) {
        if (value instanceof UndefinedValue) {
            this.getChildren().remove(ifStatements);
        }
        boolean aBoolean = ((BooleanValue) value).getValue();
        if (aBoolean) {
            this.getChildren().add(ifStatements);
        }else {
            this.getChildren().remove(ifStatements);
        }
    }

    public GUIElement getIfStatements() {
        return ifStatements;
    }

    public Expr getCondition() {
        return condition;
    }


    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
