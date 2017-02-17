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

        // TODO: Make readonly
        public OptionsList<StringLiteral> Values { get; private set; }
    }
}
