package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.*;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface FormItem extends ASTNode {
    void show(FXMLController screen);

    void idChanged(Form root, FormItem changed, String value);

    void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException,
            CyclicDependencyException;

    /* Returns the invalid type, or null if the the type is valid */
    Value.Type validateIdentifierType(String identifier, Value.Type type);

    /* Returns the redeclared identifier, or null if the identifier is not redeclared */
    String validateRedeclaration(FormItem item);

    /* Returns the two identifiers which cause the cyclic dependency, if present. Else the Pair contains one or two null values */
    Pair<String> validateCyclicDependency(FormItem item);

    /* Returns the identifier of the item which has the duplicate label */
    String validateLabel(FormItem item);

    boolean hasId(String id);

    boolean containsId(String id);

    boolean containsLabel(String label);

    boolean isDependentOn(String id);

    /* Returns the the identifier of the item this method is called on if it is a dependency of the the parameter */
    String isDependencyOf(FormItem item);
}
