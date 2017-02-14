namespace OffByOne.LanguageCore.Ast.Style.Widgets
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Widgets.Base;

    public class DropDownWidget : ListWidget
    {
        public DropDownWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }
    }
}
