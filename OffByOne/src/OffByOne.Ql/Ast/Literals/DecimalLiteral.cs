namespace OffByOne.Ql.Ast.Literals
{
    using System;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(double value)
        {
            this.Value = new DecimalValue(value);
        }

        public DecimalLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Literal must have a value.",
                    nameof(value));
            }

            this.Value = new DecimalValue(value);
        }

        public DecimalValue Value { get; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
