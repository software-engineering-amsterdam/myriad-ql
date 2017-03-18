namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Statements;

    public interface IStatementVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(Page statement, TEnvironment environment);

        TResult Visit(Section statement, TEnvironment environment);

        TResult Visit(StyleSheet statement, TEnvironment environment);
    }
}
