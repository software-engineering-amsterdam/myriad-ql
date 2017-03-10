namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.ValueTypes.Base;

    public interface IVisitorEnvironment : IEnvironment
    {
        void AddSymbol(string name, ValueType type);

        ValueType GetTypeOf(string name);
    }
}
