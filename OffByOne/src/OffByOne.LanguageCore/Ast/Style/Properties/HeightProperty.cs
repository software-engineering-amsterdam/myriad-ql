namespace OffByOne.LanguageCore.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Properties.Base;

    public class HeightProperty : Property
    {
        public HeightProperty(IntegerLiteral value)
        {
            this.Value = value;
        }

        public IntegerLiteral Value { get; set; }
    }
}
