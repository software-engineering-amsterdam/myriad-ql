namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Properties.Base;

    public class WidthProperty : Property
    {
        public WidthProperty(IntegerLiteral value)
        {
            this.Value = value;
        }

        public IntegerLiteral Value { get; set; }
    }
}
