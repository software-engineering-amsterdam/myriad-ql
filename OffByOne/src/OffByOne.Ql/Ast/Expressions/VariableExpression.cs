namespace OffByOne.Ql.Ast.Expressions
{
    using OffByOne.Ql.Visitors.Contracts;

    public class VariableExpression : Expression
    {
        public VariableExpression(string identifier)
        {
            this.Identifier = identifier;
        }

        public string Identifier { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
