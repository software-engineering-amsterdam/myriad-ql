namespace OffByOne.Ql.Values.Contracts
{
    public interface IValue
    {
        IValue Parse(string value);

        IValue Add(IValue other);

        IValue Add(IntegerValue other);

        IValue Add(DecimalValue other);

        IValue Add(MoneyValue other);

        IValue Add(DateValue other);

        IValue Add(StringValue other);

        IValue Add(BooleanValue other);

        IValue Substract(IValue other);

        IValue Substract(IntegerValue other);

        IValue Substract(DecimalValue other);

        IValue Substract(MoneyValue other);

        IValue Substract(DateValue other);

        IValue Substract(StringValue other);

        IValue Substract(BooleanValue other);

        IValue Divide(IValue other);

        IValue Divide(IntegerValue other);

        IValue Divide(DecimalValue other);

        IValue Divide(MoneyValue other);

        IValue Divide(DateValue other);

        IValue Divide(StringValue other);

        IValue Divide(BooleanValue other);

        IValue Multiply(IValue other);

        IValue Multiply(IntegerValue other);

        IValue Multiply(DecimalValue other);

        IValue Multiply(MoneyValue other);

        IValue Multiply(DateValue other);

        IValue Multiply(StringValue other);

        IValue Multiply(BooleanValue other);

        BooleanValue GreaterThanOrEqualTo(IValue other);

        BooleanValue GreaterThanOrEqualTo(IntegerValue other);

        BooleanValue GreaterThanOrEqualTo(DecimalValue other);

        BooleanValue GreaterThanOrEqualTo(MoneyValue other);

        BooleanValue GreaterThanOrEqualTo(DateValue other);

        BooleanValue GreaterThanOrEqualTo(StringValue other);

        BooleanValue GreaterThanOrEqualTo(BooleanValue other);

        BooleanValue LessThanOrEqualTo(IValue other);

        BooleanValue LessThanOrEqualTo(IntegerValue other);

        BooleanValue LessThanOrEqualTo(DecimalValue other);

        BooleanValue LessThanOrEqualTo(MoneyValue other);

        BooleanValue LessThanOrEqualTo(DateValue other);

        BooleanValue LessThanOrEqualTo(StringValue other);

        BooleanValue LessThanOrEqualTo(BooleanValue other);

        BooleanValue LessThan(IValue other);

        BooleanValue LessThan(IntegerValue other);

        BooleanValue LessThan(DecimalValue other);

        BooleanValue LessThan(MoneyValue other);

        BooleanValue LessThan(DateValue other);

        BooleanValue LessThan(StringValue other);

        BooleanValue LessThan(BooleanValue other);

        BooleanValue GreaterThan(IValue other);

        BooleanValue GreaterThan(IntegerValue other);

        BooleanValue GreaterThan(DecimalValue other);

        BooleanValue GreaterThan(MoneyValue other);

        BooleanValue GreaterThan(DateValue other);

        BooleanValue GreaterThan(StringValue other);

        BooleanValue GreaterThan(BooleanValue other);

        BooleanValue Equals(IValue other);

        BooleanValue Equals(IntegerValue other);

        BooleanValue Equals(DecimalValue other);

        BooleanValue Equals(MoneyValue other);

        BooleanValue Equals(DateValue other);

        BooleanValue Equals(StringValue other);

        BooleanValue Equals(BooleanValue other);

        BooleanValue Or(IValue other);

        BooleanValue Or(IntegerValue other);

        BooleanValue Or(DecimalValue other);

        BooleanValue Or(MoneyValue other);

        BooleanValue Or(DateValue other);

        BooleanValue Or(StringValue other);

        BooleanValue Or(BooleanValue other);

        BooleanValue And(IValue other);

        BooleanValue And(IntegerValue other);

        BooleanValue And(DecimalValue other);

        BooleanValue And(MoneyValue other);

        BooleanValue And(DateValue other);

        BooleanValue And(StringValue other);

        BooleanValue And(BooleanValue other);

        BooleanValue Not();

        IValue Negative();

        IValue Positive();
    }
}
