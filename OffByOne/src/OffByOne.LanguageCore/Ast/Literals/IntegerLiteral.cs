namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class IntegerLiteral : Literal
    {
        public IntegerLiteral(int value)
        {
            this.Value = value;
        }

        public IntegerLiteral(string value)
            : this(int.Parse(value))
        {
        }

        public int Value { get; private set; }
    }
}
