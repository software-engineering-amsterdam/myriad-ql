namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Properties;

    public interface IPropertyVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(ColorProperty expression, TContext context);

        TResult Visit(FontNameProperty expression, TContext context);

        TResult Visit(FontSizeProperty expression, TContext context);

        TResult Visit(FontStyleProperty expression, TContext context);

        TResult Visit(HeightProperty expression, TContext context);

        TResult Visit(WidthProperty expression, TContext context);
    }
}
