namespace OffByOne.Ql.Ast.Expressions.Unary
{
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;

    public class PositiveExpression : UnaryExpression
    {
        public PositiveExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
