namespace OffByOne.Ql.Checker
{
    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Visitors;
    using OffByOne.Ql.Visitors.Contracts;

    public abstract class BaseTypeCheckerVisitor
        : ILiteralVisitor<ValueType, VisitorTypeEnvironment>
    {
        public ValueType Visit(IntegerLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.IntegerType;
        }

        public ValueType Visit(MoneyLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.MoneyType;
        }

        public ValueType Visit(DecimalLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.DecimalType;
        }

        public ValueType Visit(BooleanLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.BooleanType;
        }

        public ValueType Visit(StringLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.StringType;
        }

        public ValueType Visit(DateLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.DateType;
        }

        public ValueType Visit(HexLiteral literal, VisitorTypeEnvironment context)
        {
            return TypeConstants.StringType;
        }
    }
}
