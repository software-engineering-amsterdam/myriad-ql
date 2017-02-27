package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 27-02-17.
 */
public class IdNotFoundException extends Exception {

    public IdNotFoundException() {
        super("A referenced identifier could not be  found in the form");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
