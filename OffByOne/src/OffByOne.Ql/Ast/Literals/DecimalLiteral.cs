namespace OffByOne.Ql.Ast.Literals
{
    using System;

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
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
            }

            this.Value = new DecimalValue(value);
        }

        public DecimalValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
