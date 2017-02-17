namespace OffByOne.Ql.Ast.Expressions.Unary
{
    using OffByOne.Ql.Ast.Expressions.Unary.Base;

    public class NegativeExpression : UnaryExpression
    {
        public NegativeExpression(Expression expression)
            : base(expression)
        {
        }
    }
}
