namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class StringLiteral : Expression
    {
        protected StringLiteral(string value)
        {
            this.Value = value;
        }
        public string Value { get; set; }
    }
}
