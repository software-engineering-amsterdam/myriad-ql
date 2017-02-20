namespace OffByOne.Ql.Ast.Expressions.Binary
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

    // TODO: Is this adequte?
    // The not equal expression can be done by combining the the equal and not expression, so is it jsut syntax sugar?
    public class NotEqualExpression : BinaryExpression
    {
        public NotEqualExpression(
            Expression leftExpression,
            Expression rightExpression)
            : base(leftExpression, rightExpression)
        {
        }
    }
}
