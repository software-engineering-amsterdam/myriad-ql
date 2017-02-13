namespace OffByOne.LanguageCore.Ast.Style.Widgets
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;

    public class SliderWidget : ListWidget
    {
        public SliderWidget(
            string defaultValue,
            ICollection<string> values)
            : base(defaultValue, values)
        {
        }
    }
}
