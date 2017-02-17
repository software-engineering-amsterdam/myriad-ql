namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class MultiplyExpression : BinaryExpression
    {
        public MultiplyExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
