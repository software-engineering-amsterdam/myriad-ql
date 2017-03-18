namespace OffByOne.Qls.Ast.Style.Widgets
{
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class DropDownWidget : ListWidget
    {
        public DropDownWidget(
            IList<StringLiteral> values)
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
