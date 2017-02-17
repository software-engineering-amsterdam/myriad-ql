package checkSemantics.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.types.Type;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class InvalidType extends ErrorHandler {

    private Type correctType;

    public InvalidType(CodeLocation location, Type correctType) {
        super(location);
        this.correctType = correctType;
    }

    public Type getCorrectType() {
        return correctType;
    }

    public String getMessage() {
        String message = "ERROR: Invalid type at line " + getLocation().getStartingLine() + ". Type should be " +
                correctType.toString() + ".";
        return message;
    }
}
