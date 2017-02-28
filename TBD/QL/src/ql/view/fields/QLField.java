package ql.view.fields;

import javafx.scene.Node;
import ql.ast.values.Value;

/**
 * Created by Erik on 28-2-2017.
 */
public interface QLField {
    void update(Value value);

    Node getNode();
}
