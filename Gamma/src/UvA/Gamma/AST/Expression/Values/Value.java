package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class Value<SELF extends Value> extends Expression {
    public abstract BooleanValue equals(SELF other);

    public abstract Type getType();
}
