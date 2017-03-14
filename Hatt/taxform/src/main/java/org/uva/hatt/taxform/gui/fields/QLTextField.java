package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.gui.ChangeListener;

public class QLTextField extends Field{

    private final TextField textField;

    public QLTextField() {
        this.textField = new TextField();
    }

    @Override
    public void updateCallback(ChangeListener listener) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> listener.update());
    }

    @Override
    public void update(EnvironmentsTable environmentsTable) {
        environmentsTable.add(getIdentifier(), textField.getText());
    }

    @Override
    protected void addField(ObservableList<Node> nodes) {
        nodes.add(textField);
    }
}
