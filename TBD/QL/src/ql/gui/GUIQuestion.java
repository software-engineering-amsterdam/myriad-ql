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
    public GUIQuestion(Env env, String question, String variableName) {
        QLField field;
        Type type = env.getQuestionType(variableName);

        if (type instanceof IntType) {

            field = new IntField(env, variableName);

        } else if (type instanceof FloatType) {

            field = new FloatField(env, variableName);

        } else if (type instanceof BooleanType) {

            field = new BooleanField(env, variableName);

        } else if (type instanceof StringType) {

            field = new StringField(env, variableName);
        }else {
            throw new RuntimeException();
        }

        this.getChildren().addAll(new Text(question), field.getNode());
    }
}
