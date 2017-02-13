namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

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
