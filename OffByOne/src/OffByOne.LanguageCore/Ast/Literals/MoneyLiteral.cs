namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class MoneyLiteral : Literal<decimal>
    {
        public MoneyLiteral(decimal value)
            : base(value)
        {
        }
    }
}
