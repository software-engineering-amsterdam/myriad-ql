namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Visitors.Contracts;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

    public class DropDownWidget : ListWidget
    {
        public DropDownWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }

        public override TResult Accept<TResult, TContext>(
            IWidigetVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
