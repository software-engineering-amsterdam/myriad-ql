namespace OffByOne.Qls.Ast.Style.Properties
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class WidthProperty : Property
    {
        public WidthProperty(IntegerLiteral value)
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
