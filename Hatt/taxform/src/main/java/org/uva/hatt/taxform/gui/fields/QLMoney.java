package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.gui.ChangeListener;
import org.uva.hatt.taxform.values.IntegerValue;
import org.uva.hatt.taxform.values.Value;

public class QLMoney extends Field {

    private final TextField textField;

    public QLMoney() {
        TextField textField = new TextField();
        textField.setFocusTraversable(false);

        this.textField = textField;
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
    public void update(EnvironmentsTable environmentsTable) {
        environmentsTable.add(getIdentifier(), new IntegerValue(Integer.parseInt(textField.getText())));
    }

    @Override
    protected void addField(ObservableList<Node> nodes) {
        nodes.add(textField);
    }

    @Override
    public void setValue(Value value) {
        if (value != null) {
            textField.setText(String.valueOf(value.getValue()));
        }
    }
}
