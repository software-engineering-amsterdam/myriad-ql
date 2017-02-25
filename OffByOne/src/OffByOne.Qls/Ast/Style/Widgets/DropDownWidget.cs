namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public class DropDownWidget : ListWidget
    {
        public DropDownWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }
    }
}
