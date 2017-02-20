namespace OffByOne.Ql.Ast.Expressions
{
    public class BracketExpression : Expression
    {
        public BracketExpression(Expression expression)
        {
            this.Expression = expression;
        }

        public Expression Expression { get; private set; }
    }
}
