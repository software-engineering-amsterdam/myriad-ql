package org.uva.hatt.taxform.gui;

import javafx.scene.layout.VBox;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.gui.fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Question extends VBox implements ChangeListener {

    private final List<Field> fields;
    private final Environment environmentsTable;
    private final UIVisitor uiVisitor;
    private final Form form;

    public Question(Environment environmentsTable, UIVisitor uiVisitor, Form form) {
        this.uiVisitor = uiVisitor;
        this.form = form;
        this.fields = new ArrayList<>();
        this.environmentsTable = environmentsTable;
    }

    void addField(Field field) {
        field.fillContent(getChildren());
        field.updateCallback(this);
        fields.add(field);
    }

    @Override
    public void update() {
        fields.forEach(field -> field.update(environmentsTable));
        uiVisitor.visit(form);
    }
}
