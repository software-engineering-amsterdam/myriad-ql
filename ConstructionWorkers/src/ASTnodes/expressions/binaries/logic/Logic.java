/**
 * Logic.java.
 */

package ASTnodes.expressions.binaries.logic;

import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;
import ASTnodes.CodeLocation;
import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

public abstract class Logic extends Binary {

    public Logic(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }
}
