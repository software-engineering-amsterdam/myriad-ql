namespace OffByOne.Ql.Evaluator
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class Evaluator
        : IExpressionVisitor<IValue, TypeEnvironment>
    {
        public IValue Evaluate(Expression expression, TypeEnvironment environment)
        {
            return expression.Accept(new Evaluator(), environment);
        }

        public IValue Visit(AddExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Add(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(SubtractExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Substract(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(MultiplyExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Multiply(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(DivideExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Divide(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(AndExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .And(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(OrExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Or(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(EqualExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Equals(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(NotEqualExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .Equals(expression.RightExpression.Accept(this, environment))
                .Not();
        }

        public IValue Visit(GreaterThanExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .GreaterThan(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(GreaterThanOrEqualExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .GreaterThanOrEqualTo(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(LessThanExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .LessThan(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(LessThanOrEqualExpression expression, TypeEnvironment environment)
        {
            return expression.LeftExpression
                .Accept(this, environment)
                .LessThanOrEqualTo(expression.RightExpression.Accept(this, environment));
        }

        public IValue Visit(NotExpression expression, TypeEnvironment environment)
        {
            return expression.Expression
                .Accept(this, environment)
                .Not();
        }

        public IValue Visit(NegativeExpression expression, TypeEnvironment environment)
        {
            return expression.Expression
                .Accept(this, environment)
                .Negative();
        }

        public IValue Visit(PositiveExpression expression, TypeEnvironment environment)
        {
            return expression.Expression
                .Accept(this, environment)
                .Positive();
        }

        public IValue Visit(VariableExpression expression, TypeEnvironment environment)
        {
            return environment.GetValueOf(expression.Identifier);
        }

        public IValue Visit(BracketExpression expression, TypeEnvironment environment)
        {
            return expression.Expression
                .Accept(this, environment);
        }

        public IValue Visit(IntegerLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(MoneyLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(DecimalLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(BooleanLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(StringLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(DateLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }

        public IValue Visit(HexLiteral literal, TypeEnvironment environment)
        {
            return literal.Value;
        }
    }
}
