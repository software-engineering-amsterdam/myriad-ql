namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class MoneyLiteral : Expression
    {
        // TODO: Should we verify/ensure that the float has the right precision for money?
        public MoneyLiteral(float value)
        {
            this.Value = value;
        }

        public float Value { get; set; }
    }
}
