/**
 * MoneyType.java.
 */

package ASTnodes.types;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.visitors.TypeVisitor;

public class MoneyType extends Type {

    public MoneyType() {
        super();
    }
    public MoneyType(CodeLocation location) {
        super(location);
    }


    @Override
    public String toString() {
        return "Money";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
