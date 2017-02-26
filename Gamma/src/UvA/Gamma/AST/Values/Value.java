package UvA.Gamma.AST.Values;

/**
 * Created by Tjarco, 21-02-17.
 */
public interface Value {
    void setValue(String value);

    String computableString(); //Return a string which can be used in an expression

    String toString();
}
