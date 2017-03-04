namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Literals;

    public interface ILiteralVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(IntegerLiteral literal, TContext context);

        TResult Visit(DecimalLiteral literal, TContext context);

        TResult Visit(StringLiteral literal, TContext context);

        TResult Visit(HexLiteral literal, TContext context);
    }
}
