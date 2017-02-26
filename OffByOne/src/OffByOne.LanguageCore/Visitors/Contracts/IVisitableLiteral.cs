namespace OffByOne.LanguageCore.Visitors.Contracts
{
    public interface IVisitableLiteral : IVisitable
    {
        TResult Accept<TResult, TContext>(ILiteralVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
