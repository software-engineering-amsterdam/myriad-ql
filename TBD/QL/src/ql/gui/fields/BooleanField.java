package ql.gui.fields;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.ast.environment.Env;
import ql.ast.values.BooleanValue;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;
import ql.gui.GUIChangeListener;

/**
 * Created by Erik on 28-2-2017.
 */
public class BooleanField extends ToggleGroup implements QLField{
    final RadioButton rbYes = new RadioButton("Yes");
    final RadioButton rbNo = new RadioButton("No");

    public BooleanField(Env env, String variableName) {
        rbYes.setToggleGroup(this);
        rbNo.setToggleGroup(this);

        this.selectedToggleProperty().addListener(new GUIChangeListener<Toggle>(env, variableName) {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == null) {
                    this.setValueUndefined();
                    return;
                }

                if (newValue.equals(rbYes)) {
                    this.setValue(true);
                }else if (newValue.equals(rbNo)) {
                    this.setValue(false);
                }else {
                    this.setValueUndefined();
                }
            }
        });

        if (env.hasQuestionExpr(variableName)) {
            env.addEventListener(() -> update(env.getQuestionValue(variableName)));
        }
    }

    private void update(Value value) {
        if (value instanceof UndefinedValue) {
            rbYes.selectedProperty().setValue(false);
            rbNo.selectedProperty().setValue(false);
            return;
        }

        boolean aBoolean = ((BooleanValue) value).getValue();
        if (aBoolean) {
            rbYes.selectedProperty().setValue(true);
            rbNo.selectedProperty().setValue(false);
        }else{
            rbNo.selectedProperty().setValue(true);
            rbYes.selectedProperty().setValue(false);
        }
    }

    public Node getNode(){
        return new HBox(rbYes, rbNo);
    }

}
