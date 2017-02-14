namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class DivideExpression : BinaryExpression
    {
        public DivideExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
