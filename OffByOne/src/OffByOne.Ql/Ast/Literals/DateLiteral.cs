namespace OffByOne.Ql.Ast.Literals
{
    using System;
    using System.Globalization;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class DateLiteral : Literal
    {
        public const string Format = "dd-MM-yyyy";

        public DateLiteral(DateTime value)
        {
            this.Value = new DateValue(value);
        }

        public DateLiteral(string value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value), "Literal must have a value.");
            }

            this.Value = new DateValue(DateTime.ParseExact(
                value.Trim('\''),
                DateLiteral.Format,
                CultureInfo.InvariantCulture));
        }

        public DateValue Value { get; private set; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
