/**
 * Expression.java.
 */

package ASTnodes.expressions;

import ASTnodes.LineNumber;
import ASTnodes.Node;
import ASTnodes.visitors.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(LineNumber location) {
        super(location);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
