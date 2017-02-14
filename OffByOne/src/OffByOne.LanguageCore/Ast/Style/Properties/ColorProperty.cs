namespace OffByOne.LanguageCore.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Properties.Base;

    public class ColorProperty : Property
    {
        public ColorProperty(HexLiteral value)
        {
            this.Value = value;
        }

        public HexLiteral Value { get; set; }
    }
}
