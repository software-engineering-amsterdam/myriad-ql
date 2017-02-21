namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

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

        public override TResult Accept<TResult>(ILiteralVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
