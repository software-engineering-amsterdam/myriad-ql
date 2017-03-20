package ql.gui.elements.fields;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.gui.evaluator.GUIEvaluator;
import ql.values.BooleanValue;
import ql.values.UndefinedValue;
import ql.values.Value;
import ql.gui.elements.GUIChangeListener;

/**
 * Created by Erik on 28-2-2017.
 */
public class BooleanField extends ToggleGroup implements QLField{
    final RadioButton rbYes = new RadioButton("Yes");
    final RadioButton rbNo = new RadioButton("No");

    public BooleanField(GUIEvaluator evaluator) {
        rbYes.setToggleGroup(this);
        rbNo.setToggleGroup(this);

        this.selectedToggleProperty().addListener(new GUIChangeListener<Toggle>(evaluator) {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
              this.evaluate();
            }
        });
    }

    public void update(Value value) {
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

    @Override
    public Value getValue() {
        if (rbYes.selectedProperty().getValue()) {
            return new BooleanValue(true);
        }else if(rbNo.selectedProperty().getValue()) {
            return new BooleanValue(false);
        }
        return new UndefinedValue();
    }

}
