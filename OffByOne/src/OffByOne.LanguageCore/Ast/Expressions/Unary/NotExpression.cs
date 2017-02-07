namespace OffByOne.LanguageCore.Ast.Expressions.Unary
{
    using OffByOne.LanguageCore.Ast.Expressions.Unary.Base;

    public class NotExpression : UnaryExpression
    {
        public NotExpression(Expression expression) 
            : base(expression)
        {
        }
    }
}
