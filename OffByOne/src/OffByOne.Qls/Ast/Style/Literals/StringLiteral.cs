namespace OffByOne.Qls.Ast.Style.Literals
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class StringLiteral : Literal
    {
        public StringLiteral(string value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "A non-null string must be given");
            }

            this.Value = value.Trim('"');
        }

        public string Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            ILiteralVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
