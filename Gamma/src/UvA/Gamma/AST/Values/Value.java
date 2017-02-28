package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

/**
 * Created by Tjarco, 21-02-17.
 */
public abstract class Value implements ASTNode {
    public enum Type {
        INT, DEC, MONEY, STRING, DATE, BOOL, CONDITION
    }

    public abstract Type getType(); // Force the subclass to publish it's type

    public abstract boolean conformsToType(Type type); // The subclass is responsible for determining if it can be combined with the given type

    public abstract void setValue(String value);

    public abstract String computableString(); //Return a string which can be used in an expression

    public abstract String toString();
}
