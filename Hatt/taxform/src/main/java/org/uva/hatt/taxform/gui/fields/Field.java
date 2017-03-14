package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.gui.ChangeListener;

public abstract class Field extends Pane{

    private String identifier;
    private String label;

    public abstract void updateCallback(ChangeListener listener);

    public abstract void update(EnvironmentsTable environmentsTable);

    protected abstract void addField(ObservableList<Node> nodes);

    public void fillContent(ObservableList<Node> nodes) {
        nodes.add(new Text(label));
        addField(nodes);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
