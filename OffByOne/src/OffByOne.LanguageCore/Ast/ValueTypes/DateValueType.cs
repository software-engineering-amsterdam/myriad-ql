namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class DateValueType : ValueType
    {
        public override bool Equals(object obj)
        {
            return obj.Is<DateValueType>();
        }

        public override bool Equals(ValueType obj)
        {
            return obj.Is<DateValueType>();
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "date";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
