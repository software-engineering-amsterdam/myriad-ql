package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 05-04-17.
 */
public class AbstractBaseVisitor implements Visitor {
    @Override
    public void visit(Computed computed) {

    }

    @Override
    public void visit(Question question) {

    }

    @Override
    public void visit(Condition condition) {

    }

    @Override
    public void visit(Expression expression) {

    }

    @Override
    public void visit(Value value) {

    }

    @Override
    public void visit(Type type) {

    }

    @Override
    public void visit(IdentifierValue value) {

    }
}
