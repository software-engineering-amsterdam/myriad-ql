namespace OffByOne.LanguageCore.Visitors.Contracts
{
    public interface IVisitableValueType : IVisitable
    {
        TResult Accept<TResult, TContext>(IValueTypeVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
