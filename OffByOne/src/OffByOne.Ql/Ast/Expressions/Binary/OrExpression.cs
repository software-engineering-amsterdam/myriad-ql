namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class OrExpression : BinaryExpression
    {
        public OrExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
