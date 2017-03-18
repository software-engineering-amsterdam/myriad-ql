namespace OffByOne.Ql.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Common.Visitors.Contracts;

    public class VoidValueType : ValueType
    {
        public override bool Equals(object obj)
        {
            return obj.Is<VoidValueType>();
        }

        public override bool Equals(ValueType obj)
        {
            return obj.Is<VoidValueType>();
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "void";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
