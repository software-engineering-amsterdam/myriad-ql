namespace OffByOne.Ql.Ast.Statements
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Statement : AstNode, IVisitableStatement
    {
        public abstract TResult Accept<TResult, TContext>(IStatementVisitor<TResult, TContext> visitor, TContext context)
            where TContext : IContext;
    }
}
