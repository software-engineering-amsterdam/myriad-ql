package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

/**
 * Created by Tjarco, 21-02-17.
 */
public interface Value extends ASTNode {
    void setValue(String value);

    String computableString(); //Return a string which can be used in an expression

    String toString();

    boolean canAcceptValue(String value);
}
