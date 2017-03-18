namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using HexLiteral = OffByOne.Qls.Ast.Style.Literals.HexLiteral;

    public class ColorProperty : Property
    {
        public ColorProperty(HexLiteral value)
        {
            this.Value = value;
        }

        public HexLiteral Value { get; set; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
