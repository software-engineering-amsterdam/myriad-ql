namespace OffByOne.LanguageCore.Visitors.Contracts
{
    using OffByOne.LanguageCore.Ast.Literals;

    public interface ILiteralVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(IntegerLiteral literal, TContext context);

        TResult Visit(MoneyLiteral literal, TContext context);

        TResult Visit(DecimalLiteral literal, TContext context);

        TResult Visit(BooleanLiteral literal, TContext context);

        TResult Visit(StringLiteral literal, TContext context);

        TResult Visit(DateLiteral literal, TContext context);

        TResult Visit(HexLiteral literal, TContext context);
    }
}
