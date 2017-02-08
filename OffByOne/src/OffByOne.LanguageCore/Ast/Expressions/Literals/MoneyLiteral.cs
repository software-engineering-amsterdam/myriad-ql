namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class MoneyLiteral : Expression
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = value;
        }

        public decimal Value { get; set; }
    }
}
