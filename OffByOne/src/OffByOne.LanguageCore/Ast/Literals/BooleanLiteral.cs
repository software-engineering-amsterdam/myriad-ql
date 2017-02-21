namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class BooleanLiteral : Literal
    {
        public BooleanLiteral(bool value)
        {
            this.Value = value;
        }

        public BooleanLiteral(string value)
            : this(bool.Parse(value))
        {
        }

        public bool Value { get; private set; }

        public override TResult Accept<TResult>(ILiteralVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
