package UvA.Gamma.Models.QLValues;

import java.math.BigDecimal;

/**
 * Created by Tjarco on 13-02-17.
 */
public class QLMoney extends QLValue<Double> {

    public QLMoney(){
        super(Type.MONEY);
    }

    public QLMoney(Double value){
        super(Type.MONEY, round(value));
    }

    @Override
    public void setValue(Double value) {
        this.value = round(value);
    }

    private static Double round(double value){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString() {
        if (value % 1 !=0)
            return "€" + value;
        else
            return "€" + value.intValue() + ",-";
    }
}
