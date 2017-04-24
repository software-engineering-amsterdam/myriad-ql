namespace OffByOne.Qls.Ast.Style.Statements.Base
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Common.Visitors.Contracts;

    using IVisitableStatement = OffByOne.Qls.Common.Visitors.Contracts.IVisitableStatement;

    public abstract class Statement : AstNode, Common.Visitors.Contracts.IVisitableStatement
    {
        public abstract TResult Accept<TResult, TContext>(Common.Visitors.Contracts.IStatementVisitor<TResult, TContext> visitor, TContext environment)
            where TContext : IEnvironment;
    }
}
