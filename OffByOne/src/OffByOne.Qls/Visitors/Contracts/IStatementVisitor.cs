namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;

    public interface IStatementVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(Page expression, TEnvironment environment);

        TResult Visit(Section expression, TEnvironment environment);

        TResult Visit(StyleSheet expression, TEnvironment environment);
    }
}
