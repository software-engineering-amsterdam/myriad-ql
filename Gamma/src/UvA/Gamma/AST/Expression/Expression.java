package UvA.Gamma.AST.Expression;


import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class Expression implements ASTNode {
    public abstract Value value();

    public abstract void accept(Visitor visitor);
}
