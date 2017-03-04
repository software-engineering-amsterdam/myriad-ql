namespace OffByOne.Ql.Ast.Literals
{
    using System;
    using System.Globalization;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class DateLiteral : Literal
    {
        public const string Format = "dd-MM-yyyy";

        public DateLiteral(DateTime value)
        {
            this.Value = new DateValue(value);
        }

        public DateLiteral(string dateString)
            : this(DateTime.ParseExact(
                dateString.Trim('\''),
                DateLiteral.Format,
                CultureInfo.InvariantCulture))
        {
        }

        public DateValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
