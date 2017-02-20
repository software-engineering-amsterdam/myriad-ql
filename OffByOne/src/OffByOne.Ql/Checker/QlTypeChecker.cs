namespace OffByOne.Ql.Checker
{
    using System;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Checker;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;

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
            }

            return base.CheckTypes(node);
        }

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
    }
}
