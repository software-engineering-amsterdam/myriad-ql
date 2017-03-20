package ql.gui.elements.fields;

import javafx.scene.Node;
import ql.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public interface QLField {
    Node getNode();
    Value getValue();
    void update(Value<?> value);
}
