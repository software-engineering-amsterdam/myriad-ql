package ql.gui.elements;

import javafx.scene.text.Text;
import ql.gui.elements.fields.QLField;
import ql.gui.evaluator.BaseEvaluator;
import ql.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIQuestion extends GUIElement {
    private final QLField field;
    private final String variableName;
    private final String question;

    public GUIQuestion(String question, String variableName, QLField field) {
        this.variableName = variableName;
        this.field = field;
        this.question = question;

        this.getChildren().addAll(new Text(question), field.getNode());
    }

    public String getKey() {
        return this.variableName;
    }

    public Value getValue() {
        return this.field.getValue();
    }

    public String getQuestion() {
        return question;
    }

    public <T> T accept(BaseEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
