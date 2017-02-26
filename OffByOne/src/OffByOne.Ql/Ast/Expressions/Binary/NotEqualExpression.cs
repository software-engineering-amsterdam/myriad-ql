namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Visitors.Contracts;

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

        public NotEqualExpression(IList<Expression> expressions)
            : this(expressions[0], expressions[1])
        {
        }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
