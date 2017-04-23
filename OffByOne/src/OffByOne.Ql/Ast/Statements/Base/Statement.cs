namespace OffByOne.Ql.Ast.Statements.Base
{
    using System.Collections.Generic;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public abstract class Statement : AstNode, IVisitableStatement
    {
        public abstract TResult Accept<TResult, TContext>(IStatementVisitor<TResult, TContext> visitor, TContext environment)
            where TContext : IEnvironment;

        public abstract ISet<string> GetDependencies();
    }
}
