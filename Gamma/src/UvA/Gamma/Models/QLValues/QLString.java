package UvA.Gamma.Models.QLValues;

/**
 * Created by Tjarco on 13-02-17.
 */

public class QLString extends QLValue<String> {

    public QLString(){
        super(Type.STRING);
    }

    public QLString(String value){
        super(Type.STRING, value);
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
