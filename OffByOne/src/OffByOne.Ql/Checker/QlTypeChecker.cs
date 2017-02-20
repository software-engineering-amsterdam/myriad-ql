namespace OffByOne.Ql.Checker
{
    using System;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Checker;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Expressions.Unary.Base;
    using OffByOne.Ql.Ast.Statements.Branch;

    using ValueType = OffByOne.LanguageCore.Ast.ValueTypes.Base.ValueType;

    public class QlTypeChecker : BaseChecker
    {
        public override ValueType CheckTypes(AstNode node)
        {
            switch (node)
            {
                case AddExpression expression:
                    return this.CheckBinaryMatematicalExpression(expression);
                case SubtractExpression expression:
                    return this.CheckBinaryMatematicalExpression(expression);
                case MultiplyExpression expression:
                    return this.CheckBinaryMatematicalExpression(expression);
                case DivideExpression expression:
                    return this.CheckBinaryMatematicalExpression(expression);
                case EqualExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case NotEqualExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case LessThanExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case GreaterThanExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case LessThanOrEqualExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case GreaterThanOrEqualExpression expression:
                    return this.CheckBinaryComparisonExpression(expression);
                case AndExpression expression:
                    return this.CheckBinaryBooleanLogicExpression(expression);
                case OrExpression expression:
                    return this.CheckBinaryBooleanLogicExpression(expression);
                case NotExpression expression:
                    return this.CheckUnaryBooleanLogicExpression(expression);
                case PositiveExpression expression:
                    return this.CheckUnaryMatematicalExpression(expression);
                case NegativeExpression expression:
                    return this.CheckUnaryMatematicalExpression(expression);
                case IfStatement statement:
                    return null;
                case ElseStatement statement:
                    return null;
            }

            return base.CheckTypes(node);
        }

        // TODO: Should we be able to add dates?
        private ValueType CheckBinaryMatematicalExpression(BinaryExpression expression)
        {
            var leftExpressionType = this.CheckTypes(expression.LeftExpression);
            var rightEpressionType = this.CheckTypes(expression.RightExpression);

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

        private ValueType CheckUnaryMatematicalExpression(UnaryExpression expression)
        {
            var subExpressionType = this.CheckTypes(expression.Expression);
            if (subExpressionType is NumericalValueType)
            {
                return subExpressionType;
            }

            throw new Exception("Only numerical arguments allowed");
        }

        private ValueType CheckBinaryComparisonExpression(BinaryExpression expression)
        {
            var leftExpressionType = this.CheckTypes(expression.LeftExpression);
            var rightEpressionType = this.CheckTypes(expression.RightExpression);

            if (leftExpressionType == rightEpressionType)
            {
                return new BooleanValueType();
            }

            throw new Exception("Only same type boolean expression are allowed.");
        }

        private ValueType CheckBinaryBooleanLogicExpression(BinaryExpression expression)
        {
            var leftExpressionType = this.CheckTypes(expression.LeftExpression);
            var rightEpressionType = this.CheckTypes(expression.RightExpression);

            if (leftExpressionType is BooleanValueType && rightEpressionType is BooleanValueType)
            {
                return new BooleanValueType();
            }

            throw new Exception("Only boolean arguments are allowed.");
        }

        private ValueType CheckUnaryBooleanLogicExpression(UnaryExpression expression)
        {
            var subExpressionType = this.CheckTypes(expression.Expression);
            if (subExpressionType is BooleanValueType)
            {
                return new BooleanValueType();
            }

            throw new Exception("Only boolean arguments are allowed.");
        }
    }
}
