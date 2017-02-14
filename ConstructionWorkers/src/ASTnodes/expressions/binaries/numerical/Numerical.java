/**
 * Numerical.java.
 */

package ASTnodes.expressions.binaries.numerical;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.expressions.binaries.Binary;

public abstract class Numerical extends Binary {

    public Numerical(Expression left, Expression right, CodeLocation location) {
        super(left, right, location);
    }
}
