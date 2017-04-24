namespace OffByOne.Ql.Ast.Literals
{
    using System;
    using System.Globalization;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Ast.Literals.Base;
    using OffByOne.Ql.Common;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Values;

    public class DateLiteral : Literal
    {
        public DateLiteral(DateTime value)
        {
            this.Value = new DateValue(value);
        }

        public DateLiteral(string value)
        {
            if (value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Literal must have a value.",
                    nameof(value));
            }

            this.Value = new DateValue(DateTime.ParseExact(
                value.Trim('\''),
                GlobalConstants.SystemDateFormat,
                CultureInfo.InvariantCulture));
        }

        public DateValue Value { get; }

        public override TResult Accept<TResult, TContext>(
            IExpressionVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
