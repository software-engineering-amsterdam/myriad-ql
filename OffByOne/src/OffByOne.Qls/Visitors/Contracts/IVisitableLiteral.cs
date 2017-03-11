namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IVisitableLiteral : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(ILiteralVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
