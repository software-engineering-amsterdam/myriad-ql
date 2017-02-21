namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class LiteralExpression : Expression
    {
        public LiteralExpression(Literal literal)
        {
            this.Literal = literal;
        }

        public Literal Literal { get; private set; }

        public override TResult Accept<TResult>(IExpressionVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
