package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 22-03-17.
 */
public interface Visitor {
    void visit(Computed computed);

    void visit(Question question);

    void visit(Condition condition);

    void visit(Expression expression);

    void visit(Value value);

    void visit(Type type);

    void visit(IdentifierValue value);
}
