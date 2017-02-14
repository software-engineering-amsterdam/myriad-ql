/**
 * MoneyType.java.
 */

package ASTnodes.types;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

public class MoneyType extends Type {

    public MoneyType(CodeLocation location) {
        super(location);
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
