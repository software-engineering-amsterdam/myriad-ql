namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public interface IVisitableExpression
    {
        TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
