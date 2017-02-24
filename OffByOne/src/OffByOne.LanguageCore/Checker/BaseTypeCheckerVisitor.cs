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
            return TypeConstants.IntegerType;
        }

        public ValueType Visit(MoneyLiteral literal, VisitorContext context)
        {
            return TypeConstants.MoneyType;
        }

        public ValueType Visit(DecimalLiteral literal, VisitorContext context)
        {
            return TypeConstants.FloatType;
        }

        public ValueType Visit(BooleanLiteral literal, VisitorContext context)
        {
            return TypeConstants.BooleanType;
        }

        public ValueType Visit(StringLiteral literal, VisitorContext context)
        {
            return TypeConstants.StringType;
        }

        public ValueType Visit(DateLiteral literal, VisitorContext context)
        {
            return TypeConstants.DateType;
        }

        public ValueType Visit(HexLiteral literal, VisitorContext context)
        {
            return TypeConstants.StringType;
        }
    }
}
