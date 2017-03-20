package ql.gui;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ql.visistor.environment.Env;
import ql.ast.types.*;
import ql.gui.fields.*;

/**
 * Created by Erik on 28-2-2017.
 */
public class GUIQuestion extends VBox {
    private final QLField field;

    public GUIQuestion(Env env, String question, String variableName, QLField field) {
        this.field = field;
        this.getChildren().addAll(new Text(question), field.getNode());
    }

    public QLField getField() {
        return field;
    }
}
