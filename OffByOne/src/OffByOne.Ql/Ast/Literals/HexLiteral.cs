namespace OffByOne.Ql.Ast.Literals
{
    using System.Drawing;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class HexLiteral : Literal
    {
        public HexLiteral(Color value)
        {
            this.Value = value;
        }

        public HexLiteral(string value)
            : this(ColorTranslator.FromHtml(value))
        {
        }

        public Color Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
