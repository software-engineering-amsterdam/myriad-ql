namespace OffByOne.Qls.Ast.Style.Properties.Base
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Property : AstNode, IVisitableProperty
    {
        public abstract TResult Accept<TResult, TContext>(IPropertyVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
