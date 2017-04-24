package org.uva.hatt.taxform.gui.fields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.uva.hatt.taxform.evaluation.Environment;
import org.uva.hatt.taxform.gui.ChangeListener;
import org.uva.hatt.taxform.values.Value;

public abstract class Field extends Pane{

    private String identifier;
    private String label;

    public abstract void updateCallback(ChangeListener listener);

    public abstract void update(Environment environmentsTable);

    public abstract void setValue(Value value);

    public abstract void setReadOnly();

    protected abstract void addField(ObservableList<Node> nodes);

    public void fillContent(ObservableList<Node> nodes) {
        nodes.add(new Text(label));
        addField(nodes);
    }

    String getIdentifier() {
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
