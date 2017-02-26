namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;

    public class SliderWidget : ListWidget
    {
        public SliderWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }
    }
}
