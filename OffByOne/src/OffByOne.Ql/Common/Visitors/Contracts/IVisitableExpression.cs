namespace OffByOne.Ql.Common.Visitors.Contracts
{
    public interface IVisitableExpression : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(IExpressionVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
