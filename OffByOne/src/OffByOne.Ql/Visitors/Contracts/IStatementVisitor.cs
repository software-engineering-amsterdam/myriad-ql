namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;

    public interface IStatementVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(Statement expression, TEnvironment environment);

        TResult Visit(QuestionStatement expression, TEnvironment environment);

        TResult Visit(IfStatement expression, TEnvironment environment);

        TResult Visit(FormStatement expression, TEnvironment environment);
    }
}
