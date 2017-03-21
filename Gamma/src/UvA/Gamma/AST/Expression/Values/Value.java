package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 21-03-17.
 */
public interface Value<SELF extends Value> {
    BooleanValue equals(SELF other);

    Type getType();
}
