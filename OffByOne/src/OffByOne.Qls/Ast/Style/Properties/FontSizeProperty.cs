namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Visitors.Contracts;

    using IntegerLiteral = OffByOne.Qls.Ast.Style.Literals.IntegerLiteral;

    public class FontSizeProperty : Property
    {
        public FontSizeProperty(IntegerLiteral value)
        {
            this.Value = value;
        }

        public IntegerLiteral Value { get; set; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
