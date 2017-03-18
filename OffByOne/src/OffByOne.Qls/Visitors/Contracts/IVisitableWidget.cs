namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface IVisitableWidget : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(IWidigetVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
