namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(decimal value)
        {
            this.Value = value;
        }

        public DecimalLiteral(string value)
            : this(decimal.Parse(value))
        {
        }

        public decimal Value { get; private set; }
    }
}
