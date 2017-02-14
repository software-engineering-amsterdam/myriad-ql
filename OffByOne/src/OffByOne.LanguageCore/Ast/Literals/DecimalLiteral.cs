namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;

    public class DecimalLiteral : Literal
    {
        public DecimalLiteral(double value)
        {
            this.Value = value;
        }

        public DecimalLiteral(string value)
            : this(double.Parse(value))
        {
        }

        public double Value { get; private set; }
    }
}
