namespace OffByOne.Ql.Ast.Statements
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Statement : AstNode, IVisitableStatement
    {
        public abstract TResult Accept<TResult>(IStatementVisitor<TResult> visitor);
    }
}
