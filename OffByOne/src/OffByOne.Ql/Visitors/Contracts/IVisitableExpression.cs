namespace OffByOne.Ql.Visitors.Contracts
{
    public interface IVisitableExpression
    {
        TResult Accept<TResult>(IExpressionVisitor<TResult> visitor);
    }
}
