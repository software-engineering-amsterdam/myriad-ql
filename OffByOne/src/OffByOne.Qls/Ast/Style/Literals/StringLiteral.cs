namespace OffByOne.Qls.Ast.Style.Literals
{
    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;
    using OffByOne.Qls.Visitors.Contracts;

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
