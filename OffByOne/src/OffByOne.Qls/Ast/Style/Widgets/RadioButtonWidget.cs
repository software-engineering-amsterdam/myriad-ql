namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Visitors.Contracts;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

    public class RadioButtonWidget : ListWidget
    {
        public RadioButtonWidget(
            OptionsList<StringLiteral> values)
            : base(values)
        {
        }

        public override TResult Accept<TResult, TContext>(
            IWidigetVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
