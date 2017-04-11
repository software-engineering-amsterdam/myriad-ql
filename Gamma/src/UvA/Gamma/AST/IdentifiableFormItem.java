package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.Pair;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 06-04-17.
 */
public abstract class IdentifiableFormItem implements FormItem {
    protected Identifier identifier;
    protected String label;
    protected Type type;

    public String getIdentifier() {
        return identifier.toString();
    }


    public String getLabel() {
        return label;
    }

    @Override
    public void show(FXMLController screen) {

    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {

    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public String validateRedeclaration(FormItem item) {
        return null;
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        return null;
    }

    @Override
    public String validateLabel(FormItem item) {
        return null;
    }

    @Override
    public boolean hasId(String id) {
        return false;
    }

    @Override
    public boolean containsId(String id) {
        return false;
    }

    @Override
    public boolean containsLabel(String label) {
        return false;
    }

    @Override
    public String isDependencyOf(FormItem item) {
        return null;
    }
}
