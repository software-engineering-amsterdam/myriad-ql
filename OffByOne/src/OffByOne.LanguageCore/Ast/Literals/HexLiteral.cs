namespace OffByOne.LanguageCore.Ast.Literals
{
    using System.Drawing;

    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

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

        public override TResult Accept<TResult>(ILiteralVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
