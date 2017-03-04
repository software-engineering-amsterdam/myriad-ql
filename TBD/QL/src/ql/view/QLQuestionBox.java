package ql.view;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ql.ast.types.*;
import ql.ast.environment.Environment;
import ql.view.fields.*;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLQuestionBox extends VBox {
    public QLQuestionBox(Environment environment, String question, String variableName) {
        QLField field;
        Type type = environment.getVariableType(variableName);

        if (type instanceof IntType) {

            field = new IntField(environment, variableName);

        } else if (type instanceof FloatType) {

            field = new FloatField(environment, variableName);

        } else if (type instanceof BooleanType) {

            field = new BooleanField(environment, variableName);

        } else if (type instanceof StringType) {

            field = new StringField(environment, variableName);
        }else {
            throw new RuntimeException();
        }

        this.getChildren().addAll(new Text(question), field.getNode());
    }
}
