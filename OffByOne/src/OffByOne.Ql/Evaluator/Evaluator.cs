namespace OffByOne.Ql.Evaluator
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class Evaluator
        : IExpressionVisitor<IValue, TypeEnvironment>
    {
        public IValue Visit(AddExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(SubtractExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(MultiplyExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(DivideExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(AndExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(OrExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(EqualExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(NotEqualExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(GreaterThanExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(GreaterThanOrEqualExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(LessThanExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(LessThanOrEqualExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(NotExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(NegativeExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(PositiveExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(VariableExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }

        public IValue Visit(BracketExpression expression, TypeEnvironment context)
        {
            throw new System.NotImplementedException();
        }
    }
}
