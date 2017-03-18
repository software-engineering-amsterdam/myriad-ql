namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    // TODO: Is this adequte?
    // The not equal expression can be done by combining the the equal and not expression, so is it jsut syntax sugar?
    public class NotEqualExpression : BinaryExpression
    {
        public NotEqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
