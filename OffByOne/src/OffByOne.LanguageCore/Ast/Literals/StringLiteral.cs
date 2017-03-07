namespace OffByOne.LanguageCore.Ast.Literals
{
    using OffByOne.LanguageCore.Ast.Literals.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class StringLiteral : Literal
    {
        public StringLiteral(string value)
        {
            this.Value = value.Trim('"');
        }

        public string Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
