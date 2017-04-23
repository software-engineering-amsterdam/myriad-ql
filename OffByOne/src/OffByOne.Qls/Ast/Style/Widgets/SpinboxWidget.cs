namespace OffByOne.Qls.Ast.Style.Widgets
{
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class SpinboxWidget : Widget
    {
        public override TResult Accept<TResult, TContext>(
            IWidigetVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
