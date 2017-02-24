namespace OffByOne.LanguageCore.Visitors.Contracts
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public interface IVisitorContext : IContext
    {
        void AddSymbol(string name, ValueType type);

        ValueType GetTypeOf(string name);
    }
}
