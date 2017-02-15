/**
 * StringType.java.
 */

package ASTnodes.types;

import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.TypeVisitor;

public class StringType extends Type {

    public StringType() {
        super();
    }

    public StringType(CodeLocation location) {
        super(location);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
