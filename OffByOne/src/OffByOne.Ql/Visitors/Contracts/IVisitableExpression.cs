namespace OffByOne.Ql.Visitors.Contracts
{
    public interface IVisitableExpression : IVisitable
    {
        TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
