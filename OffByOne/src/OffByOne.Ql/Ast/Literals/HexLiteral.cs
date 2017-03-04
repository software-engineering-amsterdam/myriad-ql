namespace OffByOne.Ql.Ast.Literals
{
    using System.Drawing;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class HexLiteral : Literal
    {
        public HexLiteral(Color value)
        {
            this.Value = new StringValue(value.ToString());
        }

        public HexLiteral(string value)
            : this(ColorTranslator.FromHtml(value))
        {
        }

        public StringValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
