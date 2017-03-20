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
public class GUIIf extends VBox {
    private final VBox ifStatements;
    private final GUIExpr condition;

    public GUIIf(Env env, GUIExpr condition, VBox ifStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;

    }

    private void update (Value value) {
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

    public GUIExpr getCondition() {
        return condition;
    }

}
