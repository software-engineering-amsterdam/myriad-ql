package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.gui.ChangeListener;

public class QLCheckBox extends Field {

    private final CheckBox checkBox;

    public QLCheckBox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setFocusTraversable(false);

        this.checkBox = checkBox;
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
    public void update(EnvironmentsTable environmentsTable) {
        environmentsTable.add(getIdentifier(), String.valueOf(checkBox.isSelected()));
    }

    @Override
    protected void addField(ObservableList<Node> nodes) {
        nodes.add(checkBox);
    }

    @Override
    public void setValue(String value) {

    }
}
