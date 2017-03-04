namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class BooleanLiteral : Literal
    {
        public BooleanLiteral(bool value)
        {
            this.Value = new BooleanValue(value);
        }

        public BooleanLiteral(string value)
        {
            this.Value = new BooleanValue(value);
        }

        public BooleanValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
