namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class MoneyValueType : NumericalValueType
    {
        public override bool Equals(object obj)
        {
            return obj.Is<MoneyValueType>();
        }

        public override bool Equals(ValueType obj)
        {
            return obj.Is<MoneyValueType>();
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "money";
        }

        public override TResult Accept<TResult, TContext>(
            IValueTypeVisitor<TResult, TContext> visitor,
            TContext context)
        {
            return visitor.Visit(this, context);
        }
    }
}
