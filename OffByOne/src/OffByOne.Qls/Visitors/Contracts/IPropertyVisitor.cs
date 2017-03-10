namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Properties;

    public interface IPropertyVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(ColorProperty expression, TEnvironment environment);

        TResult Visit(FontNameProperty expression, TEnvironment environment);

        TResult Visit(FontSizeProperty expression, TEnvironment environment);

        TResult Visit(FontStyleProperty expression, TEnvironment environment);

        TResult Visit(HeightProperty expression, TEnvironment environment);

        TResult Visit(WidthProperty expression, TEnvironment environment);
    }
}
