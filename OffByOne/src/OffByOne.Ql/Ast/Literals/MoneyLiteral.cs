namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class MoneyLiteral : Literal
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = value;
        }

        public MoneyLiteral(string value)
            : this(decimal.Parse(value))
        {
        }

        public decimal Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
