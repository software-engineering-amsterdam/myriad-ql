namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

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
