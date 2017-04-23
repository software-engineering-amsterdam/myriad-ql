namespace OffByOne.Ql.Ast.Expressions
{
    using System.Collections.Generic;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public abstract class Expression : AstNode, IVisitableExpression
    {
        public abstract TResult Accept<TResult, TContext>(IExpressionVisitor<TResult, TContext> visitor, TContext environment)
            where TContext : IEnvironment;

        public abstract ISet<string> GetDependencies();
    }
}
