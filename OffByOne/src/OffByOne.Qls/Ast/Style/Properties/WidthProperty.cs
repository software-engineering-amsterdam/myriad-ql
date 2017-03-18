namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using IntegerLiteral = OffByOne.Qls.Ast.Style.Literals.IntegerLiteral;

    public class WidthProperty : Property
    {
        public WidthProperty(IntegerLiteral value)
        {
            this.Value = value;
        }

        public IntegerLiteral Value { get; set; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
