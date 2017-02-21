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

    public class TestQlTypeChecker : BaseChecker
    {
        public override ValueType CheckTypes(AstNode node)
        {
            ////switch (node)
            ////{
            ////    case AddExpression expression:
            ////        return this.CheckBinaryMatematicalExpression(expression);
            ////    case SubtractExpression expression:
            ////        return this.CheckBinaryMatematicalExpression(expression);
            ////    case MultiplyExpression expression:
            ////        return this.CheckBinaryMatematicalExpression(expression);
            ////    case DivideExpression expression:
            ////        return this.CheckBinaryMatematicalExpression(expression);
            ////    case EqualExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case NotEqualExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case LessThanExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case GreaterThanExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case LessThanOrEqualExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case GreaterThanOrEqualExpression expression:
            ////        return this.CheckBinaryComparisonExpression(expression);
            ////    case AndExpression expression:
            ////        return this.CheckBinaryBooleanLogicExpression(expression);
            ////    case OrExpression expression:
            ////        return this.CheckBinaryBooleanLogicExpression(expression);
            ////    case NotExpression expression:
            ////        return this.CheckUnaryBooleanLogicExpression(expression);
            ////    case PositiveExpression expression:
            ////        return this.CheckUnaryMatematicalExpression(expression);
            ////    case NegativeExpression expression:
            ////        return this.CheckUnaryMatematicalExpression(expression);
            ////    case IfStatement statement:
            ////        return this.CheckIfStatement(statement);
            ////}

            return base.CheckTypes(node);
        }
    }
}
