namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class MoneyLiteral : Literal
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = value;
        }

        public decimal Value { get; private set; }
    }
}
