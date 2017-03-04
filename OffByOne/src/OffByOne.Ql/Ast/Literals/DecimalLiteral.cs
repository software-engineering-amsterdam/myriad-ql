namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(double value)
        {
            this.Value = new DecimalValue(value);
        }

        public DecimalLiteral(string value)
            : this(double.Parse(value))
        {
        }

        public DecimalValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
