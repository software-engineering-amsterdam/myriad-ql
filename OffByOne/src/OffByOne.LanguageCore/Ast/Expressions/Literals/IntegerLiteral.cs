namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class IntegerLiteral : Expression
    {
        public IntegerLiteral(int value)
        {
            this.Value = value;
        }

        public int Value { get; set; }
    }
}
