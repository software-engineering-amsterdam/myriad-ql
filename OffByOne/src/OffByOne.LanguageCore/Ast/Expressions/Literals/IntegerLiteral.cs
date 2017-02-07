namespace OffByOne.LanguageCore.Ast.Expressions.Literals
{
    public class IntegerLiteral : Expression
    {
        protected IntegerLiteral(int value)
        {
            this.Value = value;
        }
        public int Value { get; set; }
    }
}
