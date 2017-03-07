package UvA.Gamma.Validation;

/**
 * Created by Tjarco, 28-02-17.
 */
public class CyclicDependencyException extends Exception {
    public CyclicDependencyException() {
        super("There exists a cyclic dependency in the form");
    }

    public CyclicDependencyException(String message) {
        super(message);
    }
}
