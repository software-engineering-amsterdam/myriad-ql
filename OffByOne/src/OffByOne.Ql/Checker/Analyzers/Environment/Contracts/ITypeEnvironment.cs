namespace OffByOne.Ql.Checker.Analyzers.Environment.Contracts
{
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public interface ITypeEnvironment : IEnvironment
    {
        void AddSymbol(string name, ValueType type);

        ValueType GetTypeOf(string name);
    }
}