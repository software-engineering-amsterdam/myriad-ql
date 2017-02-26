namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

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
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
