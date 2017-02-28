namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Widgets;

    public interface IWidigetVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(CheckBoxWidget expression, TContext context);

        TResult Visit(DropDownWidget expression, TContext context);

        TResult Visit(RadioButtonWidget expression, TContext context);

        TResult Visit(DefaultWidget expression, TContext context);

        TResult Visit(SliderWidget expression, TContext context);

        TResult Visit(SpinboxWidget expression, TContext context);

        TResult Visit(TextFieldWidget expression, TContext context);
    }
}
