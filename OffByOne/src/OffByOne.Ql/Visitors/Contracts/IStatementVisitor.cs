namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;

    public interface IStatementVisitor<out TResult> : IVisitor
    {
        TResult Visit(QuestionStatement expression);

        TResult Visit(IfStatement expression);

        TResult Visit(FormStatement expression);
    }
}
