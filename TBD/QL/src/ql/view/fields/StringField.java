package ql.view.fields;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ql.ast.environment.Env;
import ql.ast.values.Value;
import ql.view.QLChangeListener;

/**
 * Created by Erik on 28-2-2017.
 */
public class StringField extends TextField implements QLField{

    public StringField(Env env, String variableName) {
        this.textProperty().addListener(new QLChangeListener<String>(env, variableName) {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                this.setValue(newValue);
            }
        });

        if (env.hasQuestionExpr(variableName)) {
            env.addEventListener(() -> {
                update(env.getQuestionValue(variableName));
            });
        }
    }

    private void update(Value value) {
        this.textProperty().setValue(String.valueOf(value.getValue()));
    }

    public Node getNode(){
        return this;
    }

}
