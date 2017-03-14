package org.uva.hatt.taxform.gui;

import javafx.scene.layout.VBox;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.gui.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Question extends VBox implements ChangeListener{

    private final List<Field> fields;
    private final EnvironmentsTable environmentsTable;

    public Question(EnvironmentsTable environmentsTable) {
        this.fields = new ArrayList<>();
        this.environmentsTable = environmentsTable;
    }

    public void addField(Field field) {
        field.fillContent(getChildren());
        field.updateCallback(this);
        fields.add(field);
    }

    @Override
    public void update() {
        fields.forEach(field -> field.update(environmentsTable));
    }
}
