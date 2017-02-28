namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IVisitableProperty : IVisitable
    {
        TResult Accept<TResult, TContext>(IPropertyVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
