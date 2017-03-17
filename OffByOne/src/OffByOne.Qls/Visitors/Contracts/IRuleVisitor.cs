namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;

    public interface IRuleVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(QuestionRule rule, TEnvironment environment);

        TResult Visit(ValueTypeRule rule, TEnvironment environment);
    }
}
