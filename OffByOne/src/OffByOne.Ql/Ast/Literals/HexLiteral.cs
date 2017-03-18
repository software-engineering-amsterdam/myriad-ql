namespace OffByOne.Ql.Ast.Literals
{
    using System;
    using System.Drawing;

    using MoreDotNet.Extensions.Common;

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
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
            }

            this.Value = new StringValue(value);
        }

        public StringValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
