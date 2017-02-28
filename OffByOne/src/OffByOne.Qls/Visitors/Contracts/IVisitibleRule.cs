namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IVisitibleRule : IVisitable
    {
        TResult Accept<TResult, TContext>(IRuleVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
