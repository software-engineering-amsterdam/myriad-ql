package UvA.Gamma.AST.Types;

import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Expression.Values.Value;

/**
 * Created by Tjarco, 21-03-17.
 */
public class IntegerType extends Type {
    @Override
    public Value defaultValue() {
        return new NumberValue("0");
    }
}
