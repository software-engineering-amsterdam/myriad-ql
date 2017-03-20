package ql.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ql.gui.evaluator.BaseEvaluator;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIForm extends GUIElement {
    private final GUIElement statements;

    public GUIForm(String name, GUIElement statements) {
        this.statements = statements;
        this.setPadding(new Insets(10));
        this.setSpacing(8);

        Text textTitle = new Text(name);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.getChildren().add(textTitle);
        this.getChildren().add(statements);
    }

    public GUIElement getStatements() {
        return statements;
    }

    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
