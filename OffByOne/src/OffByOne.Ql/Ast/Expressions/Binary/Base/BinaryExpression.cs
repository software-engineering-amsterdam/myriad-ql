namespace OffByOne.Ql.Ast.Expressions.Binary.Base
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class BinaryExpression : Expression, IVisitableExpression
    {
        protected BinaryExpression(
            Expression leftExpression,
            Expression rightExpression)
        {
            this.LeftExpression = leftExpression;
            this.RightExpression = rightExpression;
        }

        public Expression LeftExpression { get; private set; }

        public Expression RightExpression { get; private set; }

        public abstract TResult Accept<TResult>(IExpressionVisitor<TResult> visitor);
    }
}
