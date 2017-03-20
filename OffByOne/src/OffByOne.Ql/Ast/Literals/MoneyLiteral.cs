namespace OffByOne.Ql.Ast.Literals
{
    using System;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class MoneyLiteral : Literal
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = new MoneyValue(value);
        }

        public MoneyLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Literal must have a value.",
                    nameof(value));
            }

            this.Value = new MoneyValue(value);
        }

        public MoneyValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
