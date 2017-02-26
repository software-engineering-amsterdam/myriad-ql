namespace OffByOne.LanguageCore.Visitors.Contracts
{
    using OffByOne.LanguageCore.Ast.ValueTypes;

    public interface IValueTypeVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(IntegerValueType valueType, TContext context);

        TResult Visit(FloatValueType valueType, TContext context);

        TResult Visit(MoneyValueType valueType, TContext context);

        TResult Visit(BooleanValueType valueType, TContext context);

        TResult Visit(StringValueType valueType, TContext context);

        TResult Visit(DateValueType valueType, TContext context);

        TResult Visit(VoidValueType valueType, TContext context);
    }
}
