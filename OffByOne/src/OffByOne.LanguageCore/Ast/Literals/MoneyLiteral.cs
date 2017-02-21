namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class MoneyLiteral : Literal
    {
        public MoneyLiteral(decimal value)
        {
            this.Value = value;
        }

        public MoneyLiteral(string value)
            : this(decimal.Parse(value))
        {
        }

        public decimal Value { get; private set; }

        public override TResult Accept<TResult>(ILiteralVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
