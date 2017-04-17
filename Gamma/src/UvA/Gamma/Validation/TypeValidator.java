package UvA.Gamma.Validation;

import UvA.Gamma.AST.Computed;
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
}
