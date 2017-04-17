package UvA.Gamma.Validation;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Expression.Operands.BooleanOperands.BooleanOperand;
import UvA.Gamma.AST.Expression.Operands.NumberOperands.NumberOperand;
import UvA.Gamma.Visitors.BaseVisitor;

/**
 * Created by Tjarco, 11-04-17.
 */
public class TypeValidator extends BaseVisitor {
    @Override
    public void visit(Computed computed) {
        if (!computed.validateType()) {
            ErrorHandler.incompatibleType(computed.getIdentifier());
        }
    }

    @Override
    public void visit(NumberOperand operand) {
        if (!operand.validateTypes()) {
            ErrorHandler.incompatibleType(operand.toString());
        }
    }

    @Override
    public void visit(BooleanOperand operand) {
        if (!operand.validateTypes()) {
            ErrorHandler.incompatibleType(operand.toString());
        }
    }
}
