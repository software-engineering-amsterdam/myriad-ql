package ql.gui.elements;

import ql.gui.evaluator.BaseEvaluator;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIStatements extends GUIElement {
    private final GUIElement[] statements;

    public GUIStatements(GUIElement[] statements) {
        this.statements = statements;
        this.getChildren().addAll(statements);
    }

    public GUIElement[] getStatements() {
        return statements;
    }


    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
