namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DecimalLiteral : Literal<decimal>
    {
        public DecimalLiteral(decimal value)
            : base(value)
        {
        }
    }
}
