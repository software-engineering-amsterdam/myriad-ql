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

        public IValue Visit(AddExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Add(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(SubtractExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Substract(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(MultiplyExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Multiply(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(DivideExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Divide(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(AndExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .And(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(OrExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Or(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(EqualExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Equals(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(NotEqualExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .Equals(expression.RightExpression.Accept(this, context))
                .Not();
        }

        public IValue Visit(GreaterThanExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .GreaterThan(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(GreaterThanOrEqualExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .GreaterThanOrEqualTo(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(LessThanExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .LessThan(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(LessThanOrEqualExpression expression, TypeEnvironment context)
        {
            return expression.LeftExpression
                .Accept(this, context)
                .LessThanOrEqualTo(expression.RightExpression.Accept(this, context));
        }

        public IValue Visit(NotExpression expression, TypeEnvironment context)
        {
            return expression.Expression
                .Accept(this, context)
                .Not();
        }

        public IValue Visit(NegativeExpression expression, TypeEnvironment context)
        {
            return expression.Expression
                .Accept(this, context)
                .Negative();
        }

        public IValue Visit(PositiveExpression expression, TypeEnvironment context)
        {
            return expression.Expression
                .Accept(this, context)
                .Positive();
        }

        public IValue Visit(VariableExpression expression, TypeEnvironment context)
        {
            // TODO: Get value from type enviorment
            throw new System.NotImplementedException();
        }

        public IValue Visit(BracketExpression expression, TypeEnvironment context)
        {
            return expression.Expression
                .Accept(this, context);
        }

        public IValue Visit(IntegerLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(MoneyLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(DecimalLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(BooleanLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(StringLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(DateLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }

        public IValue Visit(HexLiteral literal, TypeEnvironment context)
        {
            return literal.Value;
        }
    }
}
