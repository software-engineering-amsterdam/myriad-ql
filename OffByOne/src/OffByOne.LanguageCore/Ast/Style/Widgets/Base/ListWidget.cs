namespace OffByOne.LanguageCore.Ast.Style.Widgets.Base
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public abstract class ListWidget : Widget
    {
        protected ListWidget(
            OptionsList<StringLiteral> values)
        {
            this.Values = values;
        }

        public OptionsList<StringLiteral> Values { get; private set; }
    }
}
