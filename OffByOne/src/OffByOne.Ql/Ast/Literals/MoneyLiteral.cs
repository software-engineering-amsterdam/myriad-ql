namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class MoneyLiteral : Literal
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = new MoneyValue(value);
        }

        public MoneyLiteral(string value)
            : this(decimal.Parse(value))
        {
        }

        public MoneyValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
