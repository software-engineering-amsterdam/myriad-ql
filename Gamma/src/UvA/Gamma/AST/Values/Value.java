package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.Validation.TypeChecker;

/**
 * Created by Tjarco, 21-02-17.
 */
public abstract class Value implements ASTNode {
    public enum Type {
        INTEGER, DECIMAL, MONEY, STRING, DATE, BOOL, CONDITION;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public abstract Type getType(); // Force the subclass to publish it's type

    public abstract boolean conformsToType(Type type); // The subclass is responsible for determining if it can be combined with the given type

    public abstract void setValue(String value);

    public abstract String computableString(); //Return a string which can be used in an expression

    public abstract boolean validate(String value, TypeChecker typeChecker);


    public abstract String toString();
}
