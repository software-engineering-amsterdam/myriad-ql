namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public interface IVisitableStatement : IVisitable
    {
        TResult Accept<TResult>(IStatementVisitor<TResult> visitor);
    }
}
