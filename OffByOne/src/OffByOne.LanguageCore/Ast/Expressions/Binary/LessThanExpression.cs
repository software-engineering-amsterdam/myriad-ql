namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class LessThanExpression : BinaryExpression
    {
        public LessThanExpression(
            Expression leftExpression, 
            Expression rightExpression) 
            : base(leftExpression, rightExpression)
        {
        }
    }
}
