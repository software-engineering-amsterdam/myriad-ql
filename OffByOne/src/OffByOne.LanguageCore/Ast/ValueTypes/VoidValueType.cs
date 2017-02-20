namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class VoidValueType : ValueType
    {
        public override bool Equals(object obj)
        {
            return obj is VoidValueType;
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "void";
        }
    }
}
