namespace OffByOne.Ql.Ast.Literals
{
    using System;
    using System.Drawing;

    using MoreDotNet.Extensions.Common;
    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class HexLiteral : Literal
    {
        public HexLiteral(Color value)
        {
            this.Value = new StringValue(value.ToHexString());
        }

        public HexLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Literal must have a value.",
                    nameof(value));
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
    }
}
