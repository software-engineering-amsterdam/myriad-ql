package ql.gui.elements.fields;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ql.gui.evaluator.GUIEvaluator;
import ql.values.FloatValue;
import ql.values.UndefinedValue;
import ql.values.Value;
import ql.gui.elements.GUIChangeListener;

/**
 * Created by Erik on 28-2-2017.
 */
public class FloatField extends TextField implements QLField{

    public FloatField(GUIEvaluator evaluator) {
        this.textProperty().addListener(new GUIChangeListener<String>(evaluator) {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[-+]?[0-9]*(\\.[0-9]*)?")) {
                    setText(oldValue);
                    return;
                }
                this.evaluate();
            }
        });
    }

    public void update(Value value) {
        this.textProperty().setValue(String.valueOf(value.getValue()));
    }

    public Node getNode(){
        return this;
    }

    @Override
    public Value getValue() {
        try{
            return new FloatValue(Float.valueOf(this.textProperty().getValue()));
        }catch (NumberFormatException ignore){

        }
        return new UndefinedValue();
    }
}
