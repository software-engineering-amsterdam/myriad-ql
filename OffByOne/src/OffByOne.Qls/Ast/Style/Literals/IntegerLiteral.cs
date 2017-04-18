namespace OffByOne.Qls.Ast.Style.Literals
{
    using OffByOne.Ql.Values;
    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Visitors.Contracts;

    public class IntegerLiteral : Literal
    {
        public IntegerLiteral(int value)
        {
            this.Value = new IntegerValue(value);
        }

        public IntegerLiteral(string value)
            : this(int.Parse(value))
        {
        }

        public IntegerValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
