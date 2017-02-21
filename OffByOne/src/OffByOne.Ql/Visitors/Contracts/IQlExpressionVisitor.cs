namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;

    public interface IQlExpressionVisitor<out TResult> : IVisitor
    {
        TResult Visit(AddExpression expression);

        TResult Visit(SubtractExpression expression);

        TResult Visit(MultiplyExpression expression);

        TResult Visit(DivideExpression expression);

        TResult Visit(AndExpression expression);

        TResult Visit(OrExpression expression);

        TResult Visit(EqualExpression expression);

        TResult Visit(NotEqualExpression expression);

        TResult Visit(GreaterThanExpression expression);

        TResult Visit(GreaterThanOrEqualExpression expression);

        TResult Visit(LessThanExpression expression);

        TResult Visit(LessThanOrEqualExpression expression);

        TResult Visit(NotExpression expression);

        TResult Visit(NegativeExpression expression);

        TResult Visit(PositiveExpression expression);

        TResult Visit(VariableExpression expression);

        TResult Visit(BracketExpression expression);
    }
}
