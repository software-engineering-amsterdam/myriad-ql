namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class SpinboxWidget : Widget
    {
        public override TResult Accept<TResult, TContext>(
            IWidigetVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
