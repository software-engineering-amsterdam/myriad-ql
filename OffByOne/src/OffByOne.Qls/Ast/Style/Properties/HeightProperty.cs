namespace OffByOne.Qls.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using IntegerLiteral = OffByOne.Qls.Ast.Style.Literals.IntegerLiteral;

    public class HeightProperty : Property
    {
        public HeightProperty(IntegerLiteral value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value));
            }

            this.Value = value;
        }

        public IntegerLiteral Value { get; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
