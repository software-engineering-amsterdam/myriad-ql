namespace OffByOne.LanguageCore.Ast.Expressions.Unary
{
    using OffByOne.LanguageCore.Ast.Expressions.Unary.Base;

    public class NegativeExpression : UnaryExpression
    {
        public NegativeExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
