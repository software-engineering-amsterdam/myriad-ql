package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 28-02-17.
 */
public class IncompatibleTypesException extends Exception {

    public IncompatibleTypesException() {
        super("Incompatible types were used in an expression");
    }

    public IncompatibleTypesException(String message) {
        super(message);
    }
}
