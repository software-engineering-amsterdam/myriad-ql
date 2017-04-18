namespace OffByOne.Qls.Ast.Style.Literals
{
    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(double value)
        {
            this.Value = value;
        }

        public DecimalLiteral(string value)
            : this(double.Parse(value))
        {
        }

        public double Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
