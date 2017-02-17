namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class SubtractExpression : BinaryExpression
    {
        public SubtractExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
