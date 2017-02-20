namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class BooleanLiteral : Literal<bool>
    {
        public BooleanLiteral(bool value)
            : base(value)
        {
        }

        public BooleanLiteral(string value)
            : this(bool.Parse(value))
        {
        }

        public bool Value { get; private set; }
    }
}
