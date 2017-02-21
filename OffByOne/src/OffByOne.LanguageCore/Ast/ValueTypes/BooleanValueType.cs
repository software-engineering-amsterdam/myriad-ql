namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using System;

    using OffByOne.LanguageCore.Visitors.Contracts;

    using ValueType = OffByOne.LanguageCore.Ast.ValueTypes.Base.ValueType;

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

        public override TResult Accept<TResult>(IValueTypeVisitor<TResult> visitor)
        {
            return visitor.Visit(this);
        }

        public override bool Equals(object obj)
        {
            return obj is BooleanValueType;
        }

        public override bool Equals(ValueType other)
        {
            return other is BooleanValueType;
        }
    }
}
