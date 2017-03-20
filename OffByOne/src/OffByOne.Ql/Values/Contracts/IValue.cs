namespace OffByOne.Ql.Values.Contracts
{
    public interface IValue
        : IValueOperations<IValue>,
        IValueOperations<IntegerValue>,
        IValueOperations<DecimalValue>,
        IValueOperations<MoneyValue>,
        IValueOperations<DateValue>,
        IValueOperations<BooleanValue>,
        IValueOperations<StringValue>
    {
        IValue Parse(string value);

        BooleanValue Not();

        IValue Negative();

        IValue Positive();
    }
}
