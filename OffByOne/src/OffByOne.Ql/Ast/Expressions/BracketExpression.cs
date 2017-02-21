namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class BracketExpression : Expression, IVisitableExpression
    {
        public BracketExpression(Expression expression)
        {
            this.Expression = expression;
        }

        public Expression Expression { get; private set; }

        public TResult Accept<TResult>(IExpressionVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
