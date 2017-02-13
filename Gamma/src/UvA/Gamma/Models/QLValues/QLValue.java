package UvA.Gamma.Models.QLValues;

/**
 * Created by Tjarco on 09-02-17.
 */
public abstract class QLValue<T> {
    public enum Type{
        BOOLEAN, INTEGER, DECIMAL, STRING, DATE, MONEY
    }
    private Type type;
    protected T value;

    public QLValue(Type type){
        this.type = type;
    }

    public QLValue(Type type, T value){
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public abstract void setValue(T value);

    public T getValue(){
        return value;
    }
}
