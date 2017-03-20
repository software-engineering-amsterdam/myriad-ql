namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;

    public interface IStatementVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(Statement statement, TEnvironment environment);

        TResult Visit(QuestionStatement statement, TEnvironment environment);

        TResult Visit(IfStatement statement, TEnvironment environment);

        TResult Visit(FormStatement statement, TEnvironment environment);
    }
}
