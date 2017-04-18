namespace OffByOne.Ql.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class StringLiteral : Literal
    {
        public StringLiteral(string value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
            }

            this.Value = new StringValue(value);
        }

        public StringValue Value { get; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }

        public override string ToString()
        {
            return this.Value.Value;
        }
    }
}
