namespace OffByOne.Ql.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class BooleanValueType : ValueType
    {
        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "boolean";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }

        public override bool Equals(object obj)
        {
            return obj.Is<BooleanValueType>();
        }

        public override bool Equals(ValueType other)
        {
            return other.Is<BooleanValueType>();
        }
    }
}
