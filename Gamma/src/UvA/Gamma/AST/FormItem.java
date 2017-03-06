package UvA.Gamma.AST;

import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface FormItem extends ASTNode {
    StringProperty getStringValueProperty();

    void show(FXMLExampleController screen);

    void idChanged(Form root, String id, String value);
}
