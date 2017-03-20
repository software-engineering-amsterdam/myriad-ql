package ql.gui;

import javafx.scene.layout.VBox;
import ql.ast.Expr;
import ql.ast.environment.Env;
import ql.ast.values.BooleanValue;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;


/**
 * Created by Erik on 28-2-2017.
 */
public class QLIfBox extends VBox {
    private final VBox ifStatements;

    public QLIfBox(Env env, Expr condition, VBox ifStatements) {
        this.ifStatements = ifStatements;

        env.addEventListener(() -> {
            update(env.evalExpr(condition));
        });
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

}
