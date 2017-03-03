namespace OffByOne.Ql.Checker
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Visitors;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class BaseTypeCheckerVisitor
        : ILiteralVisitor<ValueType, VisitorTypeEnv>
    {
        public ValueType Visit(IntegerLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.IntegerType;
        }

        public ValueType Visit(MoneyLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.MoneyType;
        }

        public ValueType Visit(DecimalLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.DecimalType;
        }

        public ValueType Visit(BooleanLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.BooleanType;
        }

        public ValueType Visit(StringLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.StringType;
        }

        public ValueType Visit(DateLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.DateType;
        }

        public ValueType Visit(HexLiteral literal, VisitorTypeEnv context)
        {
            return TypeConstants.StringType;
        }
    }
}
