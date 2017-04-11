package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class BooleanType extends Type {

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }
}
