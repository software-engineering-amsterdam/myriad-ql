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

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
