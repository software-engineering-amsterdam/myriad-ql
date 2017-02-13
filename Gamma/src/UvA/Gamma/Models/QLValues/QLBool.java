package UvA.Gamma.Models.QLValues;

/**
 * Created by Tjarco on 13-02-17.
 */
public class QLBool extends  QLValue<Boolean>{

    public  QLBool(){
        super(Type.BOOLEAN);
    }

    public  QLBool(Boolean value){
        super(Type.BOOLEAN, value);
    }


    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
