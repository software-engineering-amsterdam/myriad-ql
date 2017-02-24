namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Expression : AstNode, IVisitableExpression
    {
        public abstract TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
