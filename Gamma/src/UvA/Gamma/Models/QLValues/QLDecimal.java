package UvA.Gamma.Models.QLValues;

/**
 * Created by Tjarco on 13-02-17.
 */
public class QLDecimal extends QLValue<Double> {

    public QLDecimal(){
        super(Type.DECIMAL);
    }

    public QLDecimal(Double value){
        super(Type.DECIMAL, value);
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
}
