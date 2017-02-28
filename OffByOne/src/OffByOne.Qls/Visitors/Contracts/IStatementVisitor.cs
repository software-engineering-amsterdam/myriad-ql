namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;

    public interface IStatementVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(Page expression, TContext context);

        TResult Visit(Section expression, TContext context);

        TResult Visit(StyleSheet expression, TContext context);
    }
}
