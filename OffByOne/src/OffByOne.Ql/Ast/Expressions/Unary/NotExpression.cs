namespace OffByOne.Ql.Ast.Expressions.Unary
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;

    public class NotExpression : UnaryExpression
    {
        public NotExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
