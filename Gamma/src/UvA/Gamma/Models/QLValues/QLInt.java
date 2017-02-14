package UvA.Gamma.Models.QLValues;

/**
 * Created by Tjarco on 09-02-17.
 */
public class QLInt extends QLValue<Integer> {

    public QLInt(){
        super(Type.INTEGER);
    }

    public QLInt(Type type, Integer value) {
        super(type, value);
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

}
