package UvA.Gamma.Models;

/**
 * Created by Tjarco on 08-02-17.
 */
public class QLValue {
    private Object value;
    private QLType type;


    public QLValue(QLType type, Object value){
        this.type = type;
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public QLType getType() {
        return type;
    }

    //TODO: custom exception
    public boolean getBoolValue() throws Exception{
        if (this.type == QLType.BOOLEAN){
            return (Boolean) value;
        }else throw new Exception("the type of this value is not Boolean");
    }


    //TODO: custom exception
    public int getIntValue() throws Exception{
        if (this.type == QLType.INTEGER){
            return (Integer) value;
        }else throw new Exception("the type of this value is not int");
    }

    @Override
    public String toString() {
        return "<QLType>: (" + type + ") " + value;
    }
}
