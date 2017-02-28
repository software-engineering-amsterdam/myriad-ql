package UvA.Gamma.AST.Values;

/**
 * Created by Tjarco, 28-02-17.
 */
public class StringValue extends Value {

    private String value;

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public String computableString() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean conformsToType(Type type) {
        return false; // No operations on Strings
    }

    @Override
    public String toString() {
        return this.value;
    }
}
