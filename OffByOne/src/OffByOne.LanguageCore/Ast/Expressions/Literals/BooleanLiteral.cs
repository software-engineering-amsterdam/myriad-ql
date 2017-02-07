namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class BooleanLiteral : Expression
    {
        public BooleanLiteral(bool value)
        {
            this.Value = value;
        }
        public bool Value { get; set; }
    }
}
