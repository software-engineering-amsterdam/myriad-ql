namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(decimal value)
        {
            this.Value = value;
        }

        public decimal Value { get; set; }
    }
}
