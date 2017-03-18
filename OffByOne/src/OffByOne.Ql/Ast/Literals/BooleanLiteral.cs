namespace OffByOne.Ql.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class BooleanLiteral : Literal
    {
        public BooleanLiteral(bool value)
        {
            this.Value = new BooleanValue(value);
        }

        public BooleanLiteral(string value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
            }

            this.Value = new BooleanValue(value);
        }

        public BooleanValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
