namespace OffByOne.Ql.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class DecimalValueType : NumericalValueType
    {
        public override bool Equals(object obj)
        {
            return obj.Is<DecimalValueType>();
        }

        public override bool Equals(ValueType obj)
        {
            return obj.Is<DecimalValueType>();
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "float";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
