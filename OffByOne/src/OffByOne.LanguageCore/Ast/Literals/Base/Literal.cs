namespace OffByOne.LanguageCore.Ast.Literals.Base
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public abstract class Literal : Expression, IVisitableLiteral
    {
        public abstract TResult Accept<TResult>(ILiteralVisitor<TResult> visitor);
    }
}
