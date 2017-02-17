namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Properties.Base;

    public class FontSizeProperty : Property
    {
        public FontSizeProperty(IntegerLiteral value)
        {
            this.Value = value;
        }

        public IntegerLiteral Value { get; set; }
    }
}
