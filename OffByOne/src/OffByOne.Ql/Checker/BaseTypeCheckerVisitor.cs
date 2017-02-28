namespace OffByOne.Ql.Checker
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Visitors;
    using OffByOne.Ql.Visitors.Contracts;

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
            return TypeConstants.DecimalType;
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
