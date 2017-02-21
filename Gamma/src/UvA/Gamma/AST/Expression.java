package UvA.Gamma.AST;

import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface Expression extends ASTNode {
    String toString();

    void evaluate();

    StringProperty getStringValueProperty();

    void idChanged(String id, String value);
}

