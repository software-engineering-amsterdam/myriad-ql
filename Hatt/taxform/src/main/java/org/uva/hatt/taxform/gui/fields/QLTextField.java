package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.gui.ChangeListener;
import org.uva.hatt.taxform.values.StringValue;
import org.uva.hatt.taxform.values.Value;

public class QLTextField extends Field {

    private final TextField textField;

    public QLTextField() {
        this.textField = new TextField();
    }

    @Override
    public void updateCallback(ChangeListener listener) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue) {
                listener.update();
            }
        });
    }

    @Override
    public void update(Environment environmentsTable) {
        environmentsTable.add(getIdentifier(), new StringValue(textField.getText()));
    }

    @Override
    protected void addField(ObservableList<Node> nodes) {
        nodes.add(textField);
    }

    @Override
    public void setValue(Value value) {
        if (value.getValue() != null) {
            textField.setText(String.valueOf(value.getValue()));
        }
    }

    @Override
    public void setReadOnly() {
        textField.setDisable(true);
    }
}
