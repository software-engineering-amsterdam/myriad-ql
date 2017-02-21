namespace OffByOne.LanguageCore.Visitors.Contracts
{
    public interface IVisitableLiteral : IVisitable
    {
        TResult Accept<TResult>(ILiteralVisitor<TResult> visitor);
    }
}
