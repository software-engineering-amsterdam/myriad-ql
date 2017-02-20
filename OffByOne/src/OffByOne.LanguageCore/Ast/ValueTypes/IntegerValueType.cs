namespace OffByOne.LanguageCore.Ast.ValueTypes
{
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class IntegerValueType : NumericalValueType
    {
        public override bool Equals(object obj)
        {
            return obj is IntegerValueType;
        }

        public override int GetHashCode()
        {
            return int.MaxValue;
        }

        public override string ToString()
        {
            return "integer";
        }
    }
}
