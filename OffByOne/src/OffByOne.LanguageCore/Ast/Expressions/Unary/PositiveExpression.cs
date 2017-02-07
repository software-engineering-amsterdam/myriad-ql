namespace OffByOne.LanguageCore.Ast.Expressions.Unary
{
    using OffByOne.LanguageCore.Ast.Expressions.Unary.Base;

    public class PositiveExpression : UnaryExpression
    {
        public PositiveExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
