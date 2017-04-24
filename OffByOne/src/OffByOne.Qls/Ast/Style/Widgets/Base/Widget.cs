namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public abstract class Widget : AstNode, IVisitableWidget
    {
        public abstract TResult Accept<TResult, TContext>(IWidigetVisitor<TResult, TContext> visitor, TContext environment)
            where TContext : IEnvironment;
    }
}
