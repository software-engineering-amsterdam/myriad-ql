package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tjarco, 05-03-17.
 */
public class DateValue extends Value implements ASTNode {
    private Date value;
    private SimpleDateFormat format;

    public DateValue(String value) {
        format = new SimpleDateFormat("yyyy-MM-dd");
        setValue(value);
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public boolean conformsToType(Type type) {
        return false; // no operations on dates
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = format.parse(value);
        } catch (ParseException e) {} //The date might be initialized empty
    }

    @Override
    public String computableString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return format.format(this.value);
    }
}
