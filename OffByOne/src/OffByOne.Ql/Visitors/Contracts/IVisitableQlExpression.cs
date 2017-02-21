namespace OffByOne.Ql.Visitors.Contracts
{
    public interface IVisitableQlExpression
    {
        TResult Accept<TResult>(IQlExpressionVisitor<TResult> visitor);
    }
}
