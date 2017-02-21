namespace OffByOne.LanguageCore.Checker
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public abstract class BaseTypeCheckerVisitor
        : ILiteralVisitor<ValueType>
    {
        public ValueType Visit(IntegerLiteral literal)
        {
            return new IntegerValueType();
        }

        public ValueType Visit(MoneyLiteral literal)
        {
            return new MoneyValueType();
        }

        public ValueType Visit(DecimalLiteral literal)
        {
            return new FloatValueType();
        }

        public ValueType Visit(BooleanLiteral literal)
        {
            return new BooleanValueType();
        }

        public ValueType Visit(StringLiteral literal)
        {
            return new StringValueType();
        }

        public ValueType Visit(DateLiteral literal)
        {
            return new DateValueType();
        }

        public ValueType Visit(HexLiteral literal)
        {
            return new StringValueType();
        }
    }
}
