namespace OffByOne.Qls.Ast.Style.Statements.Base
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

    public abstract class Statement : AstNode, IVisitableStatement
    {
        public abstract TResult Accept<TResult, TContext>(IStatementVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
