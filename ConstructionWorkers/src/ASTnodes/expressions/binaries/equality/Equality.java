/**
 * Equality.java
 */

package ASTnodes.expressions.binaries.equality;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Equality extends Binary {

    public Equality(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }
}
