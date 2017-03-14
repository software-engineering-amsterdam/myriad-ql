package ql.view;

import javafx.scene.layout.VBox;
import ql.ast.Expr;
import ql.ast.environment.Env;
import ql.ast.environment.Environment;
import ql.ast.values.BooleanValue;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLIfElseBox extends VBox {
    private final VBox ifStatements;
    private final VBox elseStatements;
    private Boolean current = null;

    public QLIfElseBox(Env env, Expr condition, VBox ifStatements, VBox elseStatements) {
        this.ifStatements = ifStatements;
        this.elseStatements = elseStatements;

        env.addEventListener(() -> {
            update(env.evalExpr(condition));
        });
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
