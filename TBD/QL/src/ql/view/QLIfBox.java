package ql.view;

import javafx.scene.layout.VBox;
import ql.ast.Expr;
import ql.ast.environment.Environment;
import ql.ast.environment.EnvironmentEventListener;
import ql.ast.values.BooleanValue;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;


/**
 * Created by Erik on 28-2-2017.
 */
public class QLIfBox extends VBox {
    private final VBox ifStatements;

    public QLIfBox(Environment environment, Expr condition, VBox ifStatements) {
        this.ifStatements = ifStatements;

        environment.addEventListener(() -> {
            update(environment.evalExpr(condition));
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
