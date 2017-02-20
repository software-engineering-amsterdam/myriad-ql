namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class StringLiteral : Literal
    {
        public StringLiteral(string value)
        {
            this.Value = value.Trim('"');
        }

        public string Value { get; private set; }
    }
}
