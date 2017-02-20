namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    public class LessThanOrEqualExpression : BinaryExpression
    {
        public LessThanOrEqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
