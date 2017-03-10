namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Visitors.Contracts;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

    public class FontStyleProperty : Property
    {
        public FontStyleProperty(StringLiteral value)
        {
            this.Value = value;
        }

        public StringLiteral Value { get; set; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
