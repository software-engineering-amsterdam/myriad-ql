package UvA.Gamma.AST;

import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.*;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface FormItem extends ASTNode {
    StringProperty getStringValueProperty();

    void show(FXMLExampleController screen);

    void idChanged(Form root, String id, String value);

    void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException;

    boolean conformsToType(Value.Type type);

    String getId();

    boolean hasId(String id);

    boolean isDependentOn(String id);

    Value.Type getType();
}
