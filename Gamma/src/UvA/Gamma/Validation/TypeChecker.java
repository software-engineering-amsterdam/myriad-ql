package UvA.Gamma.Validation;

import UvA.Gamma.AST.Values.Value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tjarco, 05-03-17.
 */
public class TypeChecker {

    public boolean check(String value, Value.Type type) {
        switch (type) {
            case BOOL:
                return checkBool(value);
            case INTEGER:
                return checkInteger(value);
            case DECIMAL:
                return checkDouble(value);
            case DATE:
                return checkDate(value);
            default:
                return true;
        }
    }

    private boolean checkBool(String value) {
        return value.matches("^(?i)true|false");
    }

    private boolean checkInteger(String value) {
        return value.matches("^[+-]?\\d+$");
    }

    private boolean checkDouble(String value) {
        return value.matches("^[+-]?\\d+.?\\d*$");
    }

    private boolean checkDate(String value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = format.parse(value);
            return format.format(d).equals(value);
        } catch (ParseException e) {
            return false;
        }
    }

}
