namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public interface IVisitableProperty : IVisitable
    {
        TResult Accept<TResult, TContext>(IPropertyVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
