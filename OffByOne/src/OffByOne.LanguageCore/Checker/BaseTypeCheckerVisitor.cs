namespace OffByOne.LanguageCore.Checker
{
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Visitors;
    using OffByOne.LanguageCore.Visitors.Contracts;

    public abstract class BaseTypeCheckerVisitor
        : ILiteralVisitor<ValueType, VisitorContext>
    {
        public ValueType Visit(IntegerLiteral literal, VisitorContext context)
        {
            return new IntegerValueType();
        }

        public ValueType Visit(MoneyLiteral literal, VisitorContext context)
        {
            return new MoneyValueType();
        }

        public ValueType Visit(DecimalLiteral literal, VisitorContext context)
        {
            return new FloatValueType();
        }

        public ValueType Visit(BooleanLiteral literal, VisitorContext context)
        {
            return new BooleanValueType();
        }

        public ValueType Visit(StringLiteral literal, VisitorContext context)
        {
            return new StringValueType();
        }

        public ValueType Visit(DateLiteral literal, VisitorContext context)
        {
            return new DateValueType();
        }

        public ValueType Visit(HexLiteral literal, VisitorContext context)
        {
            return new StringValueType();
        }
    }
}
