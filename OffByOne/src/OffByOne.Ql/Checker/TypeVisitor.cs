namespace OffByOne.Ql.Evaluator
{
    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Visitors.Contracts;

    public class TypeVisitor
        : IExpressionVisitor<ValueType, VisitorTypeEnvironment>,
        IStatementVisitor<ValueType, VisitorTypeEnvironment>
    {
        public TypeVisitor()
            : this(new CheckerReport())
        {
        }

        public TypeVisitor(CheckerReport report)
        {
            this.Report = report;
        }

        public CheckerReport Report { get; }

        public ValueType Visit(AddExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(SubtractExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(MultiplyExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(DivideExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(AndExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(OrExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(EqualExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(NotEqualExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(GreaterThanExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(GreaterThanOrEqualExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(LessThanExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(LessThanOrEqualExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(NotExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckUnaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(NegativeExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckUnaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(PositiveExpression expression, VisitorTypeEnvironment context)
        {
            return this.CheckUnaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(VariableExpression expression, VisitorTypeEnvironment context)
        {
            var quetionType = context.GetTypeOf(expression.Identifier);
            if (quetionType == null)
            {
                this.Report.Add(new UndeclaredVariableMessage(expression));
                return TypeConstants.VoidType;
            }

            return quetionType;
        }

        public ValueType Visit(BracketExpression expression, VisitorTypeEnvironment context)
        {
            return expression.Expression.Accept(this, context);
        }

        public ValueType Visit(QuestionStatement expression, VisitorTypeEnvironment context)
        {
            context.AddSymbol(expression.Identifier, expression.Type);
            return TypeConstants.VoidType;
        }

        public ValueType Visit(IfStatement expression, VisitorTypeEnvironment context)
        {
            var result = this.CheckIfStatement(expression, context);
            expression.Statements.ForEach(x => x.Accept(this, context));
            expression.ElseStatements.ForEach(x => x.Accept(this, context));

            return result;
        }

        public ValueType Visit(FormStatement expression, VisitorTypeEnvironment context)
        {
            expression.Statements.ForEach(x => x.Accept(this, context));

            return TypeConstants.VoidType;
        }

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

        private ValueType CheckBinaryMatematicalExpression(BinaryExpression expression, VisitorTypeEnvironment context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.LeftExpression,
                    TypeConstants.NumericTypes,
                    leftExpressionType));

                return TypeConstants.VoidType;
            }

            if (rightEpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.RightExpression,
                    TypeConstants.NumericTypes,
                    rightEpressionType));
            }

            if (leftExpressionType.Is<IntegerValueType>() && rightEpressionType.Is<IntegerValueType>())
            {
                return TypeConstants.IntegerType;
            }

            if (leftExpressionType.Is<DecimalValueType>() || rightEpressionType.Is<DecimalValueType>())
            {
                return TypeConstants.DecimalType;
            }

            if (leftExpressionType.Is<MoneyValueType>() || rightEpressionType.Is<MoneyValueType>())
            {
                return TypeConstants.MoneyType;
            }

            return TypeConstants.VoidType;
        }

        private ValueType CheckUnaryMatematicalExpression(UnaryExpression expression, VisitorTypeEnvironment context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);
            if (subExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression,
                    TypeConstants.NumericTypes,
                    subExpressionType));
            }

            return subExpressionType;
        }

        private ValueType CheckBinaryComparisonExpression(BinaryExpression expression, VisitorTypeEnvironment context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightExpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType != rightExpressionType)
            {
                this.Report.Add(new InvalidTypeMessage(expression, leftExpressionType, rightExpressionType));
            }

            return TypeConstants.BooleanType;
        }

        private ValueType CheckBinaryBooleanLogicExpression(BinaryExpression expression, VisitorTypeEnvironment context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.LeftExpression,
                    TypeConstants.BooleanType,
                    leftExpressionType));

                return leftExpressionType;
            }

            if (rightEpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.RightExpression,
                    TypeConstants.BooleanType,
                    rightEpressionType));

                return rightEpressionType;
            }

            return TypeConstants.BooleanType;
        }

        private ValueType CheckUnaryBooleanLogicExpression(UnaryExpression expression, VisitorTypeEnvironment context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);

            if (subExpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression,
                    TypeConstants.BooleanType,
                    subExpressionType));

                return subExpressionType;
            }

            return TypeConstants.BooleanType;
        }

        private ValueType CheckIfStatement(IfStatement statement, VisitorTypeEnvironment context)
        {
            var conditionType = statement.Condition.Accept(this, context);
            if (conditionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(statement, TypeConstants.BooleanType, conditionType));
            }

            return TypeConstants.VoidType;
        }
    }
}
