namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class AddExpression : BinaryExpression
    {
        public AddExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
