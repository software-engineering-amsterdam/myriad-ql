package UvA.Gamma.Models.QLValues;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Tjarco on 13-02-17.
 */
public class QLDate extends QLValue<Date>{

    public QLDate(){
        super(Type.DATE);
    }

    public QLDate(String dateString){
        super(Type.DATE);
        setValue(dateString);
    }

    public void setValue(String dateString){
        try {
            this.value = DateFormat.getDateInstance().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setValue(Date value) {
        this.value = value;
    }
}
