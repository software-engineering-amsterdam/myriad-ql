/**
 * Expression.java.
 */

package QL.ASTnodes.expressions;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.Node;
import QL.ASTnodes.visitors.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(LineNumber location) {
        super(location);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
