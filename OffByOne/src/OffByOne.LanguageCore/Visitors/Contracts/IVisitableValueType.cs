namespace OffByOne.LanguageCore.Visitors.Contracts
{
    public interface IVisitableValueType : IVisitable
    {
        TResult Accept<TResult>(IValueTypeVisitor<TResult> visitor);
    }
}
