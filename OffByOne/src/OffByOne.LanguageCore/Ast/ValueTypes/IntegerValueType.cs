namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public class IntegerValueType : NumericalValueType
    {
        public override bool Equals(object obj)
        {
            return obj is IntegerValueType;
        }

        public override bool Equals(ValueType other)
        {
            return other is IntegerValueType;
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "integer";
        }

        public override TResult Accept<TResult>(IValueTypeVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }
    }
}
