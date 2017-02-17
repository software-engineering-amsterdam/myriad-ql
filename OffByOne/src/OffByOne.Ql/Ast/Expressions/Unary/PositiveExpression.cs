namespace OffByOne.Ql.Ast.Expressions.Unary
{
    using OffByOne.Ql.Ast.Expressions.Unary.Base;

    public class PositiveExpression : UnaryExpression
    {
        public PositiveExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
