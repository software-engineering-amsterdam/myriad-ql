package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.Validation.*;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface FormItem extends ASTNode {
    void show(FXMLExampleController screen);

    void idChanged(Form root, FormItem changed, String value);

    void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException, CyclicDependencyException;

    boolean validateIdentifierType(String identifier, Value.Type type);

    /* Returns the redeclared identifier, or null if the identifier is not redeclared */
    String validateRedeclaration(FormItem item);

    /* Returns the two identifiers which cause the cyclic dependency */
    Pair<String> validateCyclicDependency(FormItem item);

    boolean hasId(String id);

    boolean isDependentOn(String id);

    /* Returns the the identifier of the item this method is called on if it is a dependency of the the parameter */
    String isDependencyOf(FormItem item);

    Value.Type getType();
}
