package UvA.Gamma.Validation;

import UvA.Gamma.AST.Values.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tjarco, 05-03-17.
 */
public class TypeChecker {

    /*
        Validate the new value given by the user. Empty values are always allowed (since it is the inital state)
     */
    public boolean check(Value value, String newValue) {
        return newValue.isEmpty() || value.validate(newValue, this);
    }

    public boolean checkBool(String value) {
        return value.matches("^(?i)true|false");
    }

    public boolean checkInteger(String value) {
        return value.matches("^[+-]?\\d+$");
    }

    public boolean checkDouble(String value) {
        return value.matches("^[+-]?\\d+.?\\d*$");
    }

    public boolean checkDate(String value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = format.parse(value);
            return format.format(d).equals(value);
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean checkMoney(String value) {
        return value.matches("^\\d+((\\.\\d{2})|(\\.-))?$");
    }

}
