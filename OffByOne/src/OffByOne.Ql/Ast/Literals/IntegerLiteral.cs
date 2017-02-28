namespace OffByOne.Ql.Ast.Literals
{
    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class IntegerLiteral : Literal
    {
        public IntegerLiteral(int value)
        {
            this.Value = value;
        }

        public IntegerLiteral(string value)
            : this(int.Parse(value))
        {
        }

        public int Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
