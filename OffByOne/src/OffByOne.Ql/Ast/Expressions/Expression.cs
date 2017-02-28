namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Expression : AstNode, IVisitableExpression
    {
        public abstract TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
