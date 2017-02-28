namespace OffByOne.Ql.Visitors.Contracts
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;

    public interface IExpressionVisitor<out TResult, in TContext> : IVisitor
        where TContext : IContext
    {
        TResult Visit(AddExpression expression, TContext context);

        TResult Visit(SubtractExpression expression, TContext context);

        TResult Visit(MultiplyExpression expression, TContext context);

        TResult Visit(DivideExpression expression, TContext context);

        TResult Visit(AndExpression expression, TContext context);

        TResult Visit(OrExpression expression, TContext context);

        TResult Visit(EqualExpression expression, TContext context);

        TResult Visit(NotEqualExpression expression, TContext context);

        TResult Visit(GreaterThanExpression expression, TContext context);

        TResult Visit(GreaterThanOrEqualExpression expression, TContext context);

        TResult Visit(LessThanExpression expression, TContext context);

        TResult Visit(LessThanOrEqualExpression expression, TContext context);

        TResult Visit(NotExpression expression, TContext context);

        TResult Visit(NegativeExpression expression, TContext context);

        TResult Visit(PositiveExpression expression, TContext context);

        TResult Visit(VariableExpression expression, TContext context);

        TResult Visit(BracketExpression expression, TContext context);
    }
}
