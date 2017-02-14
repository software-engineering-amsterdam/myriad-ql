namespace OffByOne.LanguageCore.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Properties.Base;

    public class FontNameProperty : Property
    {
        public FontNameProperty(StringLiteral value)
        {
            this.Value = value;
        }

        public StringLiteral Value { get; set; }
    }
}
