namespace OffByOne.LanguageCore
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;

    public class TypeConstants
    {
        public static readonly BooleanValueType BooleanType = new BooleanValueType();
        public static readonly StringValueType StringType = new StringValueType();
        public static readonly DecimalValueType DecimalType = new DecimalValueType();
        public static readonly IntegerValueType IntegerType = new IntegerValueType();
        public static readonly MoneyValueType MoneyType = new MoneyValueType();
        public static readonly DateValueType DateType = new DateValueType();
        public static readonly VoidValueType VoidType = new VoidValueType();

        public static readonly IEnumerable<NumericalValueType> NumericTypes = new NumericalValueType[]
        {
            IntegerType,
            MoneyType,
            DecimalType
        };
    }
}
