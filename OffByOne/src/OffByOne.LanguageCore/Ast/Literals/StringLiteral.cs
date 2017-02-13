namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class StringLiteral : Literal<string>
    {
        public StringLiteral(string value)
            : base(value)
        {
        }
    }
}
