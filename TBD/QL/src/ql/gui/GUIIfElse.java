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
public class GUIIfElse extends VBox {
    private final VBox ifStatements;
    private final VBox elseStatements;
    private Boolean current = null;
    private final GUIExpr condition;

    public GUIIfElse(Env env, GUIExpr condition, VBox ifStatements, VBox elseStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;
        this.elseStatements = elseStatements;
    }

    private void update (Value value) {
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
}
