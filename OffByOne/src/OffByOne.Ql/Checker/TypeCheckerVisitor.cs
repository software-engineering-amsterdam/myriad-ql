namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Checker;
    using OffByOne.LanguageCore.Checker.Messages;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.LanguageCore.Visitors;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Checker.Messages;
    using OffByOne.Ql.Visitors.Contracts;

    using ValueType = OffByOne.LanguageCore.Ast.ValueTypes.Base.ValueType;

    public class TypeCheckerVisitor
        : BaseTypeCheckerVisitor,
        IExpressionVisitor<ValueType, VisitorContext>,
        IStatementVisitor<ValueType, VisitorContext>
    {
        public TypeCheckerVisitor()
            : this(new CheckerReport())
        {
        }

        public TypeCheckerVisitor(CheckerReport report)
        {
            this.Report = report;
        }

        public CheckerReport Report { get; }

        public ValueType Visit(AddExpression expression, VisitorContext context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(SubtractExpression expression, VisitorContext context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(MultiplyExpression expression, VisitorContext context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(DivideExpression expression, VisitorContext context)
        {
            return this.CheckBinaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(AndExpression expression, VisitorContext context)
        {
            return this.CheckBinaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(OrExpression expression, VisitorContext context)
        {
            return this.CheckBinaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(EqualExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(NotEqualExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(GreaterThanExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(GreaterThanOrEqualExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(LessThanExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(LessThanOrEqualExpression expression, VisitorContext context)
        {
            return this.CheckBinaryComparisonExpression(expression, context);
        }

        public ValueType Visit(NotExpression expression, VisitorContext context)
        {
            return this.CheckUnaryBooleanLogicExpression(expression, context);
        }

        public ValueType Visit(NegativeExpression expression, VisitorContext context)
        {
            return this.CheckUnaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(PositiveExpression expression, VisitorContext context)
        {
            return this.CheckUnaryMatematicalExpression(expression, context);
        }

        public ValueType Visit(VariableExpression expression, VisitorContext context)
        {
            var quetionType = context.GetTypeOf(expression.Identifier);
            if (quetionType == null)
            {
                this.Report.Add(new UndeclaredVariableMessage(expression));
                return TypeConstants.VoidType;
            }

            return quetionType;
        }

        public ValueType Visit(BracketExpression expression, VisitorContext context)
        {
            return expression.Expression.Accept(this, context);
        }

        public ValueType Visit(LiteralExpression expression, VisitorContext context)
        {
            return expression.Literal.Accept(this, context);
        }

        public ValueType Visit(QuestionStatement expression, VisitorContext context)
        {
            context.AddSymbol(expression.Identifier, expression.Type);
            return TypeConstants.VoidType;
        }

        public ValueType Visit(IfStatement expression, VisitorContext context)
        {
            var result = this.CheckIfStatement(expression, context);
            expression.Statements.ForEach(x => x.Accept(this, context));
            expression.ElseStatements.ForEach(x => x.Accept(this, context));

            return result;
        }

        public ValueType Visit(FormStatement expression, VisitorContext context)
        {
            expression.Statements.ForEach(x => x.Accept(this, context));

            return TypeConstants.VoidType;
        }

        private ValueType CheckBinaryMatematicalExpression(BinaryExpression expression, VisitorContext context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression.LeftExpression,
                    TypeConstants.NumericTypes,
                    leftExpressionType,
                    LogLevel.Error));

                return TypeConstants.VoidType;
            }

            if (rightEpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression.RightExpression,
                    TypeConstants.NumericTypes,
                    rightEpressionType,
                    LogLevel.Error));
            }

            if (leftExpressionType.Is<IntegerValueType>() && rightEpressionType.Is<IntegerValueType>())
            {
                return TypeConstants.IntegerType;
            }

            if (leftExpressionType.Is<FloatValueType>() || rightEpressionType.Is<FloatValueType>())
            {
                return TypeConstants.FloatType;
            }

            if (leftExpressionType.Is<MoneyValueType>() || rightEpressionType.Is<MoneyValueType>())
            {
                return TypeConstants.MoneyType;
            }

            return TypeConstants.VoidType;
        }

        private ValueType CheckUnaryMatematicalExpression(UnaryExpression expression, VisitorContext context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);
            if (subExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression,
                    TypeConstants.NumericTypes,
                    subExpressionType,
                    LogLevel.Error));
            }

            return subExpressionType;
        }

        private ValueType CheckBinaryComparisonExpression(BinaryExpression expression, VisitorContext context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType != rightEpressionType)
            {
                this.Report.Add(new InvaildTypeMessage(expression, leftExpressionType, rightEpressionType, LogLevel.Error));
            }

            return TypeConstants.BooleanType;
        }

        private ValueType CheckBinaryBooleanLogicExpression(BinaryExpression expression, VisitorContext context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression.LeftExpression,
                    TypeConstants.BooleanType,
                    leftExpressionType,
                    LogLevel.Error));

                return leftExpressionType;
            }

            if (rightEpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression.RightExpression,
                    TypeConstants.BooleanType,
                    rightEpressionType,
                    LogLevel.Error));

                return rightEpressionType;
            }

            return TypeConstants.BooleanType;
        }

        private ValueType CheckUnaryBooleanLogicExpression(UnaryExpression expression, VisitorContext context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);

            if (subExpressionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression,
                    TypeConstants.BooleanType,
                    subExpressionType,
                    LogLevel.Error));

                return subExpressionType;
            }

            return new BooleanValueType();
        }

        private ValueType CheckIfStatement(IfStatement statement, VisitorContext context)
        {
            var conditionType = statement.Condition.Accept(this, context);
            if (conditionType.IsNot<BooleanValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(statement, new BooleanValueType(), conditionType, LogLevel.Error));
            }

            return new VoidValueType();
        }
    }
}
