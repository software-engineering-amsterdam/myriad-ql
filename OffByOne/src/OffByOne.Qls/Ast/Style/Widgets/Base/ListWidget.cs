namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using OffByOne.Ql.Ast.Literals;

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
