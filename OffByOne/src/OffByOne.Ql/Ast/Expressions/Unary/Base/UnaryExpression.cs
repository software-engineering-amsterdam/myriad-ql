namespace OffByOne.Ql.Ast.Expressions.Unary.Base
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class UnaryExpression : Expression, IVisitableExpression
    {
        protected UnaryExpression(Expression expression)
        {
            this.Expression = expression;
        }

        public Expression Expression { get; private set; }

        public abstract TResult Accept<TResult>(IExpressionVisitor<TResult> visitor);
    }
}
