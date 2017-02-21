namespace OffByOne.LanguageCore.Ast.ValueTypes.Base
{
    using OffByOne.LanguageCore.Visitors.Contracts;

    public abstract class ValueType : AstNode, IVisitableValueType
    {
        public abstract TResult Accept<TResult>(IValueTypeVisitor<TResult> visitor);
    }
}
