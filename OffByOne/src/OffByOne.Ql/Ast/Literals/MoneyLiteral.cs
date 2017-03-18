namespace OffByOne.Ql.Ast.Literals
{
    using System;

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
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
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
