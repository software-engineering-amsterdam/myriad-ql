namespace OffByOne.Ql.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class IntegerValueType : NumericalValueType
    {
        public override bool Equals(object obj)
        {
            return obj.Is<IntegerValueType>();
        }

        public override bool Equals(ValueType other)
        {
            return other.Is<IntegerValueType>();
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "integer";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
