namespace OffByOne.Ql.Ast.Expressions.Unary.Base
{
    public abstract class UnaryExpression : Expression
    {
        protected UnaryExpression(Expression expression)
        {
            this.Expression = expression;
        }

        public Expression Expression { get; private set; }
    }
}
