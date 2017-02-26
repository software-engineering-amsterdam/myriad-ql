namespace OffByOne.LanguageCore.Ast.Literals.Base
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public abstract class Literal : AstNode, IVisitableLiteral
    {
        public abstract TResult Accept<TResult, TContext>(ILiteralVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
