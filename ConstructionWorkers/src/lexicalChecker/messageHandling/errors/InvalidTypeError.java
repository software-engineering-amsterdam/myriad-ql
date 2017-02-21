/**
 * InvalidTypeError.java.
 */

package lexicalChecker.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.types.Type;

public class InvalidTypeError extends ErrorHandler {

    private final Type validType;

    public InvalidTypeError(CodeLocation location, Type validType) {
        super(location);
        this.validType = validType;
    }

    public Type getValidType() {
        return validType;
    }

    public String getMessage() {
        return "ERROR: Invalid type at line " + getLocation().getStartingLine() + ". Type should be " +
                validType.toString() + ".";
    }
}
