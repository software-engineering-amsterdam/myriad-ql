namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Widget : AstNode, IVisitableWidget
    {
        public abstract TResult Accept<TResult, TContext>(IWidigetVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
