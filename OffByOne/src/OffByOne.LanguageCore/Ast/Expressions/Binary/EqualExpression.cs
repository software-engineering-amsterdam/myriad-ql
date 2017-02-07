namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class EqualExpression : BinaryExpression
    {
        public EqualExpression(
            Expression leftExpression, 
            Expression rightExpression) 
            : base(leftExpression, rightExpression)
        {
        }
    }
}
