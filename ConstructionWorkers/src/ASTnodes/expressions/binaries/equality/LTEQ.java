package ASTnodes.expressions.binaries.equality;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.visitors.AllVisitors;

/**
 * Created by LGGX on 10-Feb-17.
 */
public class LTEQ extends Equality {

    private final Expression left;
    private final Expression right;

    public LTEQ(Expression left, Expression right, CodeLocation location) {
        super(location);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return left + "<=" + right;
    }

}