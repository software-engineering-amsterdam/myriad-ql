package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 27-02-17.
 */
public class IdRedeclaredException extends Exception {
    public IdRedeclaredException() {
        super("An identifier is redeclared ");
    }

    public IdRedeclaredException(String message) {
        super(message);
    }
}
