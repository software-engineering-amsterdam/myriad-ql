namespace OffByOne.Qls.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

    public class FontNameProperty : Property
    {
        public FontNameProperty(StringLiteral value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value));
            }

            this.Value = value;
        }

        public StringLiteral Value { get; set; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
