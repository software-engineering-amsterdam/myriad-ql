namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class StringValueType : ValueType
    {
        public override bool Equals(object obj)
        {
            return obj is StringValueType;
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "string";
        }
    }
}
