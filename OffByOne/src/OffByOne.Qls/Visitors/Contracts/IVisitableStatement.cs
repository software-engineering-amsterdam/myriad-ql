namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IVisitableStatement : IVisitable
    {
        TResult Accept<TResult, TContext>(IStatementVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
