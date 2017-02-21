namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class Expression : AstNode, IVisitableExpression
    {
        public abstract TResult Accept<TResult>(IExpressionVisitor<TResult> visitor);
    }
}
