namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

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
