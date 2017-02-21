namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class DateValueType : ValueType
    {
        public override bool Equals(object obj)
        {
            return obj is DateValueType;
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "date";
        }

        public override TResult Accept<TResult>(IValueTypeVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }

        public override bool Equals(ValueType obj)
        {
            return obj is DateValueType;
        }
    }
}
