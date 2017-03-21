namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;

    public interface IExpressionVisitor<out TResult, in TEnvironment> : IVisitor
        where TEnvironment : IEnvironment
    {
        TResult Visit(Expression expression, TEnvironment environment);

        TResult Visit(AddExpression expression, TEnvironment environment);

        TResult Visit(SubtractExpression expression, TEnvironment environment);

        TResult Visit(MultiplyExpression expression, TEnvironment environment);

        TResult Visit(DivideExpression expression, TEnvironment environment);

        TResult Visit(AndExpression expression, TEnvironment environment);

        TResult Visit(OrExpression expression, TEnvironment environment);

        TResult Visit(EqualExpression expression, TEnvironment environment);

        TResult Visit(NotEqualExpression expression, TEnvironment environment);

        TResult Visit(GreaterThanExpression expression, TEnvironment environment);

        TResult Visit(GreaterThanOrEqualExpression expression, TEnvironment environment);

        TResult Visit(LessThanExpression expression, TEnvironment environment);

        TResult Visit(LessThanOrEqualExpression expression, TEnvironment environment);

        TResult Visit(NotExpression expression, TEnvironment environment);

        TResult Visit(NegativeExpression expression, TEnvironment environment);

        TResult Visit(PositiveExpression expression, TEnvironment environment);

        TResult Visit(VariableExpression expression, TEnvironment environment);

        TResult Visit(BracketExpression expression, TEnvironment environment);

        TResult Visit(IntegerLiteral literal, TEnvironment environment);

        TResult Visit(MoneyLiteral literal, TEnvironment environment);

        TResult Visit(DecimalLiteral literal, TEnvironment environment);

        TResult Visit(BooleanLiteral literal, TEnvironment environment);

        TResult Visit(StringLiteral literal, TEnvironment environment);

        TResult Visit(DateLiteral literal, TEnvironment environment);

        TResult Visit(HexLiteral literal, TEnvironment environment);
    }
}
