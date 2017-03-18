namespace OffByOne.Qls.Common.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Widgets;

    public interface IWidigetVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(CheckBoxWidget widget, TEnvironment environment);

        TResult Visit(DropDownWidget widget, TEnvironment environment);

        TResult Visit(RadioButtonWidget widget, TEnvironment environment);

        TResult Visit(DefaultWidget widget, TEnvironment environment);

        TResult Visit(SliderWidget widget, TEnvironment environment);

        TResult Visit(SpinboxWidget widget, TEnvironment environment);

        TResult Visit(TextFieldWidget widget, TEnvironment environment);
    }
}
