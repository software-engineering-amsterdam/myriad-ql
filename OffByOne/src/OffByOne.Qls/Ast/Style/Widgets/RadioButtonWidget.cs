namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public class RadioButtonWidget : ListWidget
    {
        public RadioButtonWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }
    }
}
