namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public interface IVisitableWidget : IVisitable
    {
        TResult Accept<TResult, TContext>(IWidigetVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
