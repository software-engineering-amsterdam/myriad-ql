namespace OffByOne.Ql.Ast.Literals
{
    using System;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class IntegerLiteral : Literal
    {
        public IntegerLiteral(int value)
        {
            this.Value = new IntegerValue(value);
        }

        public IntegerLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Literal must have a value.",
                    nameof(value));
            }

            this.Value = new IntegerValue(value);
        }

        public IntegerValue Value { get; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
