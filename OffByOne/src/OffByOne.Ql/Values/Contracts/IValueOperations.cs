namespace OffByOne.Ql.Values.Contracts
{
    using OffByOne.Ql.Values.Base;

    public interface IValueOperations<T>
        where T : class
    {
        IValue Add(T other);

        IValue Substract(T other);

        IValue Divide(T other);

        IValue Multiply(T other);

        BooleanValue GreaterThanOrEqualTo(T other);

        BooleanValue LessThanOrEqualTo(T other);

        BooleanValue LessThan(T other);

        BooleanValue GreaterThan(T other);

        BooleanValue Equals(T other);

        BooleanValue Or(T other);

        BooleanValue And(T other);
    }
}
