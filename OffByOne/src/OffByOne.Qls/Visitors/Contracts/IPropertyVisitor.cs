namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Properties;

    public interface IPropertyVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(ColorProperty property, TEnvironment environment);

        TResult Visit(FontNameProperty property, TEnvironment environment);

        TResult Visit(FontSizeProperty property, TEnvironment environment);

        TResult Visit(FontStyleProperty property, TEnvironment environment);

        TResult Visit(HeightProperty property, TEnvironment environment);

        TResult Visit(WidthProperty property, TEnvironment environment);
    }
}
