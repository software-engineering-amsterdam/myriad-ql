namespace OffByOne.LanguageCore.Ast.Style.Widgets
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;

    public class SliderWidget : ListWidget
    {
        public SliderWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }
    }
}
