namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;

    public interface IRuleVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(QuestionRule expression, TContext context);

        TResult Visit(ValueTypeRule expression, TContext context);
    }
}
