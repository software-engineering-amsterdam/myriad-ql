namespace OffByOne.LanguageCore.Ast.Literals
{
    using System.Drawing;

    using OffByOne.LanguageCore.Ast.Literals.Base;

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
    }
}
