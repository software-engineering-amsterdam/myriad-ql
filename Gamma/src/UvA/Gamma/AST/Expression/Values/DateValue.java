package UvA.Gamma.AST.Expression.Values;

import UvA.Gamma.AST.Types.DateType;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.Visitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tjarco, 17-04-17.
 */
public class DateValue extends Value<DateValue> {
    private Date value;

    public DateValue(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            value = format.parse(date);
        } catch (ParseException e) {
            System.err.print("Invalid date syntax");
        }
    }

    @Override
    public BooleanValue equals(DateValue other) {
        return new BooleanValue(value.compareTo(other.value) == 0);
    }

    @Override
    public Type getType() {
        return new DateType();
    }

    @Override
    public boolean conformsToType(Type type) {
        return type.equalsType(this.getType());
    }

    @Override
    public Value value() {
        return this;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
