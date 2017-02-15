/**
 * Expression.java.
 */

package ASTnodes.expressions;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(CodeLocation location) {
        super(location);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
