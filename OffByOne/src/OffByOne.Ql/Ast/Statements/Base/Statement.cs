namespace OffByOne.Ql.Ast.Statements.Base
{
    using System.Collections.Generic;

    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Statement : AstNode, IVisitableStatement
    {
        public abstract TResult Accept<TResult, TContext>(IStatementVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;

        public abstract ISet<string> GetDependencies();
    }
}
