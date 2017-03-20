package ql.gui.fields;


import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ql.gui.GUIChangeListener;
import ql.gui.GUIEvaluator;
import ql.values.IntValue;
import ql.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public class IntField extends TextField implements QLField{

    public IntField(GUIEvaluator evaluator) {
        this.textProperty().addListener(new GUIChangeListener<String>(evaluator) {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[-+]?[0-9]*"))  {
                    setText(oldValue);
                    return;
                }
                this.evaluate();
            }
        });
    }

    private void update(Value value) {
        this.textProperty().setValue(String.valueOf(value.getValue()));
    }

    public Node getNode(){
        return this;
    }

    @Override
    public Value getValue() {
        return new IntValue(Integer.valueOf(this.textProperty().getValue()));
    }

}
