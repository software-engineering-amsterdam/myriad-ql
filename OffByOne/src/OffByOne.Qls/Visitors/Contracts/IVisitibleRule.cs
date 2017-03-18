namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface IVisitibleRule : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(IRuleVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
