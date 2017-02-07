namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class DecimalLiteral : Expression
    {
        protected DecimalLiteral(decimal value)
        {
            this.Value = value;
        }

        public decimal Value { get; set; }
    }
}
