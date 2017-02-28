package ql.view.fields;


import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import ql.ast.environment.Environment;
import ql.ast.values.Value;
import ql.view.QLChangeListener;

/**
 * Created by Erik on 28-2-2017.
 */
public class IntField extends TextField implements QLField{

    public IntField(Environment environment, String variableName) {
        this.textProperty().addListener(new QLChangeListener<String>(environment, variableName) {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[-+]?[0-9]*")) {
                    setText(oldValue);
                    return;
                }
                try {
                    this.setValue(Integer.valueOf(newValue));
                }catch (NumberFormatException e){
                    this.setValueUndefined();
                }
            }
        });
    }

    @Override
    public void update(Value value) {
        this.textProperty().setValue(String.valueOf(value.getValue()));
    }

    public Node getNode(){
        return this;
    }

}
