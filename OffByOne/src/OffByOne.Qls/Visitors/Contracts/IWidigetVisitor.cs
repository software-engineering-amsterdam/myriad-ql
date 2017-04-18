namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Widgets;

    public interface IWidigetVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(CheckBoxWidget expression, TEnvironment environment);

        TResult Visit(DropDownWidget expression, TEnvironment environment);

        TResult Visit(RadioButtonWidget expression, TEnvironment environment);

        TResult Visit(DefaultWidget expression, TEnvironment environment);

        TResult Visit(SliderWidget expression, TEnvironment environment);

        TResult Visit(SpinboxWidget expression, TEnvironment environment);

        TResult Visit(TextFieldWidget expression, TEnvironment environment);
    }
}
