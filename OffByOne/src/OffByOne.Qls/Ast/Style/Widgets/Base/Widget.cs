namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Widget : AstNode, IVisitableWidget
    {
        public abstract TResult Accept<TResult, TContext>(IWidigetVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
