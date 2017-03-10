namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IVisitableProperty : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(IPropertyVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
