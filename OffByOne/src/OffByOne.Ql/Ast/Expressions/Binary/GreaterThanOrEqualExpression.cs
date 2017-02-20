namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
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
