namespace OffByOne.Qls.Ast.Style.Widgets.Base
{
    using OffByOne.Ql.Ast.Literals;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

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
