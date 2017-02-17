namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class GreaterThanOrEqualExpression : BinaryExpression
    {
        public GreaterThanOrEqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
