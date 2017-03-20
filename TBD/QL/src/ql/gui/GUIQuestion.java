package ql.gui;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ql.values.StringValue;
import ql.values.Value;
import ql.visistor.environment.Env;
import ql.ast.types.*;
import ql.gui.fields.*;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIQuestion extends VBox {
    private final QLField field;
    private final String variableName;

    public GUIQuestion(String question, String variableName, QLField field) {
        this.variableName = variableName;
        this.field = field;
        this.getChildren().addAll(new Text(question), field.getNode());
    }

    public Value getValue() {
        return this.field.getValue();
    }

    public QLField getField() {
        return field;
    }
}
