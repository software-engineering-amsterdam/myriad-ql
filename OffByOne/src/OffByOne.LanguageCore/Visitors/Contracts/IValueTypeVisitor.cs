namespace OffByOne.LanguageCore.Visitors.Contracts
{
    using OffByOne.LanguageCore.Ast.ValueTypes;

    public interface IValueTypeVisitor<out TResult, in TContext> : IVisitor
    {
        TResult Visit(IntegerValueType valueType);

        TResult Visit(FloatValueType valueType);

        TResult Visit(MoneyValueType valueType);

        TResult Visit(BooleanValueType valueType);

        TResult Visit(StringValueType valueType);

        TResult Visit(DateValueType valueType);

        TResult Visit(VoidValueType valueType);
    }
}
