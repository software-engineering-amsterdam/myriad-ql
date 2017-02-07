namespace OffByOne.LanguageCore.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Binary.Base;

    public class AndExpression : BinaryExpression
    {
        public AndExpression(
            Expression leftExpression, 
            Expression rightExpression) 
            : base(leftExpression, rightExpression)
        {
        }
    }
}
