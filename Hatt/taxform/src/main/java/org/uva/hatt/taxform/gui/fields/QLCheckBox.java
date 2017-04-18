package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.gui.ChangeListener;
import org.uva.hatt.taxform.values.BooleanValue;
import org.uva.hatt.taxform.values.Value;

public class QLCheckBox extends Field {

    private final CheckBox checkBox;

    public QLCheckBox() {
        this.checkBox = new CheckBox();
    }

    @Override
    public void updateCallback(ChangeListener listener) {
        checkBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue) {
                listener.update();
            }
        });
    }

    @Override
    public void update(Environment environmentsTable) {
        environmentsTable.add(getIdentifier(), new BooleanValue(checkBox.isSelected()));
    }

    @Override
    protected void addField(ObservableList<Node> nodes) {
        nodes.add(checkBox);
    }

    @Override
    public void setValue(Value value) {
        if (value.getValue() != null) {
            checkBox.setSelected((Boolean) value.getValue());
        }
    }

    @Override
    public void setReadOnly() {
        checkBox.setDisable(true);
    }
}
