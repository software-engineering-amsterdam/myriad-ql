namespace OffByOne.Qls.Ast.Style.Literals
{
    using System;

    using MoreDotNet.Wrappers;

    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class IntegerLiteral : Literal
    {
        public IntegerLiteral(int value)
        {
            this.Value = value;
        }

        public IntegerLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A non-null, non-empty string must be given", nameof(value));
            }

            this.Value = int.Parse(value);
        }

        public int Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
