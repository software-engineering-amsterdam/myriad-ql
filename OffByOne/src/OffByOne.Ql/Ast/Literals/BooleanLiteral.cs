namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Visitors.Contracts;

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

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
