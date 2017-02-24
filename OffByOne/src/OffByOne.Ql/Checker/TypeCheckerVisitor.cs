namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Extensions.Common;

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
            // TODO: Need to add a enviorment to the visitor
            throw new System.NotImplementedException();
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
            return new VoidValueType();
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

            return new VoidValueType();
        }

        private ValueType CheckBinaryMatematicalExpression(BinaryExpression expression, VisitorContext context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType is IntegerValueType && rightEpressionType is IntegerValueType)
            {
                return new IntegerValueType();
            }

            if (leftExpressionType is FloatValueType || rightEpressionType is FloatValueType)
            {
                return new FloatValueType();
            }

            if (leftExpressionType is MoneyValueType || rightEpressionType is MoneyValueType)
            {
                return new MoneyValueType();
            }

            throw new Exception("Ivalid argument");
        }

        private ValueType CheckUnaryMatematicalExpression(UnaryExpression expression, VisitorContext context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);
            if (subExpressionType.IsNot<NumericalValueType>())
            {
                this.Report.Add(new InvaildTypeMessage(
                    expression,
                    new List<ValueType> { new IntegerValueType(), new FloatValueType(), new MoneyValueType() },
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

            return new BooleanValueType();
        }

        private ValueType CheckBinaryBooleanLogicExpression(BinaryExpression expression, VisitorContext context)
        {
            var leftExpressionType = expression.LeftExpression.Accept(this, context);
            var rightEpressionType = expression.RightExpression.Accept(this, context);

            if (leftExpressionType is BooleanValueType && rightEpressionType is BooleanValueType)
            {
                return new BooleanValueType();
            }

            throw new Exception("Only boolean arguments are allowed.");
        }

        private ValueType CheckUnaryBooleanLogicExpression(UnaryExpression expression, VisitorContext context)
        {
            var subExpressionType = expression.Expression.Accept(this, context);
            if (subExpressionType is BooleanValueType)
            {
                return new BooleanValueType();
            }

            throw new Exception("Only boolean arguments are allowed.");
        }

        private ValueType CheckIfStatement(IfStatement statement, VisitorContext context)
        {
            var conditionType = statement.Condition.Accept(this, context);
            if (!(conditionType is BooleanValueType))
            {
                this.Report.Add(new InvaildTypeMessage(statement, new BooleanValueType(), conditionType, LogLevel.Error));
            }

            return new VoidValueType();
        }
    }
}
