namespace OffByOne.Qls.Visitors.Contracts
{
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public interface IVisitableLiteral : IVisitable
    {
        TResult Accept<TResult, TEnvironment>(ILiteralVisitor<TResult, TEnvironment> visitor, TEnvironment environment)
            where TEnvironment : IEnvironment;
    }
}
