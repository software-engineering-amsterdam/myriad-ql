package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class Type implements ASTNode {

    public boolean equalsType(Type type) {
        return type.getClass().getName().equals(this.getClass().getName());
    }

    public abstract Value defaultValue();
}
