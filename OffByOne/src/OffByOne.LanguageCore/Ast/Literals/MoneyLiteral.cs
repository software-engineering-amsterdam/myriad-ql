namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class MoneyLiteral : Literal<decimal>
    {
        public MoneyLiteral(decimal value)
            : base(value)
        {
        }

        public MoneyLiteral(string value)
            : this(decimal.Parse(value))
        {
        }

        public decimal Value { get; private set; }
    }
}
