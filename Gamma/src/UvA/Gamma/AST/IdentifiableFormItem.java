package UvA.Gamma.AST;

import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 06-04-17.
 */
public abstract class IdentifiableFormItem implements FormItem {
    protected Identifier identifier;
    String label;
    protected Type type;

    public String getIdentifier() {
        return identifier.toString();
    }


    public String getLabel() {
        return label;
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {

    }
}
