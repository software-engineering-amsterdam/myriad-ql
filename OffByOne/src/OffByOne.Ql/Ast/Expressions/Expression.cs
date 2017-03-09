namespace OffByOne.Ql.Ast.Expressions
{
    using System.Collections.Generic;

    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Expression : AstNode, IVisitableExpression
    {
        public abstract TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;

        public abstract ISet<string> GetDependencies();
    }
}
