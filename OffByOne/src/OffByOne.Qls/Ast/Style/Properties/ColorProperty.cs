namespace OffByOne.Qls.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using HexLiteral = OffByOne.Qls.Ast.Style.Literals.HexLiteral;

    public class ColorProperty : Property
    {
        public ColorProperty(HexLiteral value)
        {
            if (value == null)
            {
                throw new ArgumentNullException(nameof(value));
            }

            this.Value = value;
        }

        public HexLiteral Value { get; }

        public override TResult Accept<TResult, TContext>(
            IPropertyVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
