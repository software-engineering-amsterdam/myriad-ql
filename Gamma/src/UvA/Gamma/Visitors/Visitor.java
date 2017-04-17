package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Operands.BooleanOperands.BooleanOperand;
import UvA.Gamma.AST.Expression.Operands.NumberOperands.NumberOperand;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;

/**
 * Created by Tjarco, 22-03-17.
 */
public interface Visitor {
    void visit(IdentifiableFormItem item);

    void visit(Computed computed);

    void visit(Question question);

    void visit(Condition condition);

    void visit(Expression expression);

    void visit(NumberOperand operand);

    void visit(BooleanOperand operand);

    void visit(Value value);

    void visit(Type type);

    void visit(IdentifierValue value);
}
