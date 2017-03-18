namespace OffByOne.Qls.Ast.Style.Literals
{
    using System;

    using MoreDotNet.Wrappers;

    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(double value)
        {
            this.Value = value;
        }

        public DecimalLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A non-null, non-empty string must be given", nameof(value));
            }

            this.Value = double.Parse(value);
        }

        public double Value { get; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
