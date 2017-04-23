namespace OffByOne.Ql.Common.Visitors.Contracts
{
    using OffByOne.Ql.Ast.ValueTypes.Base;

    public interface IVisitorTypeEnvironment : IEnvironment
    {
        void AddSymbol(string name, ValueType type);

        ValueType GetTypeOf(string name);
    }
}
