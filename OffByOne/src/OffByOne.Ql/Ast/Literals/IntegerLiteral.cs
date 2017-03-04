namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

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
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
