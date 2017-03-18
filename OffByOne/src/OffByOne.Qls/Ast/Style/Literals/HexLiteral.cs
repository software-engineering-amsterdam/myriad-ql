namespace OffByOne.Qls.Ast.Style.Literals
{
    using System;
    using System.Drawing;

    using MoreDotNet.Wrappers;

    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class HexLiteral : Literal
    {
        public HexLiteral(Color value)
        {
            this.Value = value;
        }

        public HexLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A non-null, non-empty string must be given", nameof(value));
            }

            this.Value = ColorTranslator.FromHtml(value);
        }

        public Color Value { get; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
