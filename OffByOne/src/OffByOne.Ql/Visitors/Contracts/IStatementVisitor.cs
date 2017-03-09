namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.Statements;

    public interface IStatementVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(QuestionStatement expression, TContext context);

        TResult Visit(IfStatement expression, TContext context);

        TResult Visit(FormStatement expression, TContext context);
    }
}
