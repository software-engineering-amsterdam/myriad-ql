namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;

    public interface IStatementVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(QuestionStatement expression, TContext context);

        TResult Visit(IfStatement expression, TContext context);

        TResult Visit(FormStatement expression, TContext context);
    }
}
