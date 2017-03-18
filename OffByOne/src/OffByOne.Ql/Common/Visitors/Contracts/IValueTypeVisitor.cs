namespace OffByOne.Ql.Common.Visitors.Contracts
{
    using OffByOne.Ql.Ast.ValueTypes;

    public interface IValueTypeVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(IntegerValueType valueType, TEnvironment environment);

        TResult Visit(DecimalValueType valueType, TEnvironment environment);

        TResult Visit(MoneyValueType valueType, TEnvironment environment);

        TResult Visit(BooleanValueType valueType, TEnvironment environment);

        TResult Visit(StringValueType valueType, TEnvironment environment);

        TResult Visit(DateValueType valueType, TEnvironment environment);

        TResult Visit(VoidValueType valueType, TEnvironment environment);
    }
}
