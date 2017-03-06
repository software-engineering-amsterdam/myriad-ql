namespace OffByOne.Ql.Evaluator
{
    using System.Collections.Generic;

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
        IStatementVisitor<VoidValueType, VisitorTypeEnvironment>
    {
        private static readonly IEnumerable<ValueType> NumericValueTypes = new List<ValueType>()
        {
            new IntegerValueType(),
            new DecimalValueType(),
            new MoneyValueType()
        };

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
                return new VoidValueType();
            }

            return quetionType;
        }

        public ValueType Visit(BracketExpression expression, VisitorTypeEnvironment context)
        {
            return expression.Expression.Accept(this, context);
        }

        public VoidValueType Visit(QuestionStatement expression, VisitorTypeEnvironment context)
        {
            context.AddSymbol(expression.Identifier, expression.Type);
            return new VoidValueType();
        }

        public VoidValueType Visit(IfStatement expression, VisitorTypeEnvironment context)
        {
            var result = this.CheckIfStatement(expression, context);
            expression.Statements.ForEach(x => x.Accept(this, context));
            expression.ElseStatements.ForEach(x => x.Accept(this, context));

            return new VoidValueType();
        }

        public VoidValueType Visit(FormStatement expression, VisitorTypeEnvironment context)
        {
            expression.Statements.ForEach(x => x.Accept(this, context));

            return new VoidValueType();
        }

        public ValueType Visit(IntegerLiteral literal, VisitorTypeEnvironment context)
        {
            return new IntegerValueType();
        }

        public ValueType Visit(MoneyLiteral literal, VisitorTypeEnvironment context)
        {
            return new MoneyValueType();
        }

        public ValueType Visit(DecimalLiteral literal, VisitorTypeEnvironment context)
        {
            return new DecimalValueType();
        }

        public ValueType Visit(BooleanLiteral literal, VisitorTypeEnvironment context)
        {
            return new BooleanValueType();
        }

        public ValueType Visit(StringLiteral literal, VisitorTypeEnvironment context)
        {
            return new StringValueType();
        }

        public ValueType Visit(DateLiteral literal, VisitorTypeEnvironment context)
        {
            return new DateValueType();
        }

        public ValueType Visit(HexLiteral literal, VisitorTypeEnvironment context)
        {
            return new StringValueType();
        }

        private ValueType CheckBinaryMatematicalExpression(BinaryExpression expression, VisitorTypeEnvironment context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightExpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType != new IntegerValueType()
                && leftExpressionType != new DecimalValueType()
                && leftExpressionType != new MoneyValueType())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.LeftExpression,
                    NumericValueTypes,
                    leftExpressionType));

                return new VoidValueType();
            }

            if (leftExpressionType != new IntegerValueType()
                && leftExpressionType != new DecimalValueType()
                && leftExpressionType != new MoneyValueType())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.RightExpression,
                    NumericValueTypes,
                    rightExpressionType));
            }

            if (leftExpressionType == new IntegerValueType()
                && rightExpressionType == new IntegerValueType())
            {
                return new IntegerValueType();
            }

            if (leftExpressionType == new DecimalValueType()
                || rightExpressionType == new DecimalValueType())
            {
                return new DecimalValueType();
            }

            if (leftExpressionType == new MoneyValueType()
                || rightExpressionType == new MoneyValueType())
            {
                return new MoneyValueType();
            }

            return new VoidValueType();
        }

        private ValueType CheckUnaryMatematicalExpression(UnaryExpression expression, VisitorTypeEnvironment context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);
            if (subExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression,
                    NumericValueTypes,
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
                this.Report.Add(new InvalidTypeMessage(
                    expression,
                    leftExpressionType,
                    rightExpressionType));
            }

            return new BooleanValueType();
        }

        private ValueType CheckBinaryBooleanLogicExpression(BinaryExpression expression, VisitorTypeEnvironment context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType != new BooleanValueType())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.LeftExpression,
                    new BooleanValueType(),
                    leftExpressionType));

                return leftExpressionType;
            }

            if (rightEpressionType != new BooleanValueType())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression.RightExpression,
                    new BooleanValueType(),
                    rightEpressionType));

                return rightEpressionType;
            }

            return new BooleanValueType();
        }

        private ValueType CheckUnaryBooleanLogicExpression(UnaryExpression expression, VisitorTypeEnvironment context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);

            if (subExpressionType != new BooleanValueType())
            {
                this.Report.Add(new InvalidTypeMessage(
                    expression,
                    new BooleanValueType(),
                    subExpressionType));

                return subExpressionType;
            }

            return new BooleanValueType();
        }

        private ValueType CheckIfStatement(IfStatement statement, VisitorTypeEnvironment context)
        {
            var conditionType = statement.Condition.Accept(this, context);
            if (conditionType != new BooleanValueType())
            {
                this.Report.Add(new InvalidTypeMessage(statement, new BooleanValueType(), conditionType));
            }

            return new VoidValueType();
        }
    }
}
