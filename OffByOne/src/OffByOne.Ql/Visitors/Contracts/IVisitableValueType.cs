namespace OffByOne.Ql.Visitors.Contracts
{
    public interface IVisitableValueType : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(IValueTypeVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
