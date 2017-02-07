namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class GreaterThanExpression : BinaryExpression
    {
        public GreaterThanExpression(
            Expression leftExpression, 
            Expression rightExpression) 
            : base(leftExpression, rightExpression)
        {
        }
    }
}
